<template>
  <navbar/>
  <div class="container-fluid pt-3  main">
    <div class="row mb-2">
      <div class="col-12">
        <div class="mx-2 ">
          <div class="title ">
            DEPARTMENT OF INFORMATION TECHNOLOGIES
          </div>
          <div class="sub__title">
            DASHBOARD
          </div>
        </div>
      </div>
    </div>
    <div class="row">

        <DashBoard
            v-for="classroom in classrooms"
            :key="classroom"
            :classroom="classroom"/>

    </div>
  </div>
</template>

<script>
import axios from "axios";
import DashBoard from "../components/DashBoard";
import Navbar from "../components/Navbar"

export default {
  name: "Main",
  components: {DashBoard, Navbar},
  data(){
    return{
      classrooms: []
    }
  },
  mounted() {
    //Когда будет Api
    axios.get('/classroom').then(response => this.classrooms = response.data)

    setInterval(this.updateData, 1500)

  },
  methods:{
    updateData(){
      axios.get('/classroom').then(response => {
        for (let i = 0; i < response.data.length; i++){
          this.classrooms[i].speed = response.data[i].speed;
          this.classrooms[i].computers = response.data[i].computers;
        }

      })
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Roboto+Condensed');
.title{
  font-size: 1.625rem;
  line-height: 1.625rem;
  color: #8F908C;
}
.sub__title{
  font-size: 1.875rem;
  line-height: 1.875rem;
  color: #E8EDEA;
}

.main{
  overflow-y: auto;
  height: 100vh;
  width: 100%;
  z-index: 4444;
  background-color: #1F2125;
  font-family: 'Roboto Condensed', sans-serif;
}
</style>