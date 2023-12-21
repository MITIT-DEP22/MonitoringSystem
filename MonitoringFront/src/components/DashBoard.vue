<template>
  <div class="col-6 mb-3 px-3">
    <a :href="'/classroom/' + classroom.number">
      <div class="ms-1 dashboard">
        <div class="reversed__text">
          NETWORK USAGE
        </div>
        <progress-bar
            :value="classroom.speed"
            :size="150"
            :max-value="200"
        />
        <div class="available__computers__block">
          <div>AVAILABLE COMPUTERS</div>
          <div class="computers__divs">
            <div class="computer"
                 v-for="computer in classroom.computers"
                 :key="computer"
            >
              <div class="circle"
                   :class="{
             'green' : computer.loaded,
             'red': !computer.loaded}"
              />
              <div class="computer__number">
                {{ classroom.number + '-' + computer.number }}
              </div>
            </div>
          </div>
        </div>
        <div class="classroom__div">
          <div class="classroom">{{ classroom.number }}</div>
        </div>
      </div>
    </a>


  </div>
</template>

<script>
import "vue3-circle-progress/dist/circle-progress.css";
import ProgressBar from "./UI/ProgressBar";

export default {
  props: {
    classroom: Object
  },
  name: "DashBoard",
  components: {ProgressBar}
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Roboto+Condensed');

a{
  text-decoration: none;
}

.dashboard {
  display: flex;
  height: 15.625rem;
  border-radius: 0.25rem;
  background-color: #333A43;
}

.reversed__text {
  writing-mode: vertical-lr;
  transform: rotate(180deg);
  padding: 0 0.35rem 0 0.35rem;
  display: flex;
  height: 100%;
  justify-content: center;
  font-size: 18px;
  color: #93979B;
  width: 2.5rem;
}

.available__computers__block {
  margin-left: 1.5rem;
  color: #93979B;
  font-size: 1.125rem;
  padding: 1rem 1.25rem;
  width: 100%;
}

.computers__divs {
  margin: 1.25rem 0;
  display: flex;
  flex-wrap: wrap;
}

.circle {
  width: 0.625rem;
  height: 0.625rem;
  -webkit-border-radius: 1.563rem;
  -moz-border-radius: 1.563rem;
  border-radius: 1.563rem;
  display: flex;
  align-self: center;
  margin-right: 0.5rem;
}

.red {
  background: #FA5555;
}

.green {
  background: #65ef56;
}

.computer {
  display: flex;
  width: 5rem;
  background-color: #1F2125;
  padding: 0.375rem;
  margin-right: 0.5rem;
  margin-bottom: 0.5rem;
  font-size: 1rem;
  border-radius: 5px;
  color: #FFFFFF;
  box-shadow: 0 0 3px black;
}

.classroom {
  position: relative;
  width: 100%;
  font-size: 1.625rem;
  color: #FFFFFF;
  padding: 6px 2.25rem;
  display: flex;
  background: #7A49EC;
  border-radius: 3px;
  left: 0.625rem;
  bottom: 0.625rem;
}
</style>