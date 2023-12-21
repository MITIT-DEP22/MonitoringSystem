<template>
  <navbar/>
  <div class="container-fluid main text-white min-vh-100">
    <div class="row">

      <div class="col-2 mt-2">
        <div class="btn-group">
          <button @click="setPeriod()" class="btn btn-outline-secondary">
            <fas class="fs-1" icon="clock"></fas>
          </button>
          <input type="number" class="mx-1" v-model="period" min="0" max="10080">
          <label class="mt-3">хв</label>
        </div>
      </div>

      <div class="col-2 mt-2">

      </div>
      <div class="col-7">
      </div>
      <div class="col-1">

      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="mb-5">
          <div v-for="classroom in classrooms" :key="classroom.number">
            <div v-if="classroom.isActive">
              <h1>Number of classroom {{ classroom.number }}</h1>


              <line-chart class="graphic text-white" :data="classroom.data"></line-chart>
            </div>

            <div v-if="!classroom.isActive" class="d-flex justify-content-center">
              <strong class="me-2">Loading...</strong>
              <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Navbar from "../components/Navbar"

export default {
  components:{
    Navbar
  },
  name: "Graphics",
  data() {
    return {
      classrooms: [{
        data: [
          {name: 'Call asds', data: {}},
          {name: 'Call parents', data: {}}
        ],
        number: 0,
        isActive: false
      }],
      period: 20,
    }
  },

  methods: {
    getHumanTime(date) {
      return date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
    },

    getData() {
      setInterval(() => {
        axios.get('/history/last-hour').then((response) => {
          let data = response.data;
          let listOfClassrooms = Object.keys(response.data);

          this.classrooms = new Array(listOfClassrooms.length);

          for (let i = 0; i < listOfClassrooms.length; i++) {
            this.classrooms[i] = {
              data: [2],
              number: listOfClassrooms[i],
              isActive: false
            };

            this.classrooms[i].data[0] = {
              name: 'Download',
              data: []
            };
            this.classrooms[i].data[0].data = {}
            for (let j = 0; j < data[listOfClassrooms[i]].length; j++) {
              this.classrooms[i].data[0].data[this.getHumanTime(new Date(data[listOfClassrooms[i]][j].date))] = data[listOfClassrooms[i]][j].averageDownload;
            }

            this.classrooms[i].data[1] = {
              name: 'Upload',
              data: []
            };
            this.classrooms[i].data[1].data = {}
            for (let j = 0; j < data[listOfClassrooms[i]].length; j++) {
              this.classrooms[i].data[1].data[this.getHumanTime(new Date(data[listOfClassrooms[i]][j].date))] = data[listOfClassrooms[i]][j].averageUpload;
            }

            this.classrooms[i].isActive = true;
          }
        })
      }, 5000);
    },

    getPeriod() {
      let period = localStorage.getItem('period');
      if (period !== null && period !== undefined && period !== '') {
        this.period = period;
        this.setPeriod();
      }
    },

    setPeriod() {
      console.log(this.period);
      axios.post('/history/period/' + this.period).then(response => {
        console.log(response);
        localStorage.setItem('period', this.period);
      }).catch(e => {
        console.log(e.response)
      })
    }
  },

  mounted() {
    this.getPeriod();

    this.getData();
  }
}
</script>

<style scoped>
.main {
  background-color: #1F2125;
}

.graphic {
  background: #333a43;
}
</style>