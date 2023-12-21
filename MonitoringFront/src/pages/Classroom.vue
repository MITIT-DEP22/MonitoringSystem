<template>
  <navbar/>
  <div style="background-color: #1f2125; min-height: 100vh;
" class="pb-5">
    <label class="dashboard-label ms-5 mt-3">DASHBOARD OF <strong style="color:#FFFFFF">{{ numberClassroom }}</strong>
      CLASSROOM</label>
    <button @click="getStaticApi" class="ms-3  btn-outline- bg-transparent border-0">
      <fas icon="redo-alt" style="color:#333a43" class="fs-1" />
    </button>
    <div class="container-fluid">
      <div class="row ps-2">
        <div v-for="pc in computers" :key="pc.number" class="col-6 ">
          <PCInfo
              :pc="pc"
              :number-classroom="numberClassroom"
          ></PCInfo>
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
  data() {
    return {
      computers: [],
      numberClassroom: this.$route.params.classroomNumber,

    }
  },
  methods: {
    getVariableApi() {
      setInterval(() => axios.get('/classroom/' + this.numberClassroom + '/variable').then(response => {
        let data = response.data;
        for (let i = 0; i < data.length; i++) {
          this.computers[i].cpu = data[i].cpu;
          this.computers[i].network = data[i].network;
          this.computers[i].ram = data[i].ram;
          this.computers[i].loaded = data[i].loaded;
        }
      }).catch(error => {
        console.log(error);
      }), 1000);
    },
    getStaticApi() {
      axios.get('/classroom/' + this.numberClassroom).then(response => {
        this.computers = response.data.computerClassroomResponse;
        this.classroomNumber = response.data.classroomNumber;
      }).catch(error => {
        console.log(error);
      })

    }

  },
  mounted() {
    this.getStaticApi();
    this.getVariableApi();
  }

}
</script>


<style>

.dashboard-label {
  font-size: 35px;
  color: #666e6e;
  font-family: 'Roboto Condensed', sans-serif;
}

</style>