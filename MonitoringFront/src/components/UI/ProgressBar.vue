<template>
  <div class="mx-3 position-relative d-flex flex-column justify-content-center">
    <circle-progress
        :size="size"
        :percent="value / maxValue * 100"
        :viewport="true"
        :empty-color="'#2D333A'"
        :fill-color="'#55F992'"
        :is-gradient="true"
        :gradient="{
         angle: 90,
         startColor: '#55F992',
         stopColor: '#34a65d'
       }"
    />
    <div class="position-absolute w-100 d-flex justify-content-center progress__speed">
      {{ percentShow ? value + '%' : value }}
    </div>
    <div v-if="!percentShow && !disk" class="position-absolute w-100 d-flex justify-content-center progress__unit">
      Mb/s
    </div >
    <div v-if="disk" class="position-absolute w-100 d-flex justify-content-center progress__unit">
     Gb
    </div >
  </div>
</template>

<script>

import CircleProgress from "vue3-circle-progress";

export default {
  name: "progress-bar",
  components: {
    CircleProgress
  },
  props: {
    size: {
      type: Number,
      default: 175
    },
    value: Number,
    percentShow: Boolean,
    maxValue: {
      type: Number,
      default: 100
    },
    disk:Boolean,
  }
}
</script>

<style scoped>
.progress__speed {
  font-size: 1.75rem;
  color: #FFFFFF;
  top: 6.25rem;
}

.progress__unit {
  margin-top: 3.75rem;
  color: #ACAAAD;
  font-size: 1.125rem;
  top: 4.688rem;
}
</style>