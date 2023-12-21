<template>

  <div class="fluid-container main-container text-center  mt-5">
    <button data-bs-toggle="collapse" class="btn-outline- bg-transparent border-0" style=""
            :data-bs-target="'#collapseOne'+[pc.numberPc]">
      <div class="d-flex">
        <div class="classroom-label px-3 mb-4 ">
          <label>{{ numberClassroom }}-{{ pc.numberPc }}</label>
        </div>

        <fas icon="power-off" :class="'fs-1 '+ [pc.loaded ? 'text-success' : 'text-danger'] "/>


      </div>

      <div class="row text-center">
        <div class="col-3 col-xs-12">
          <label class="info-label">NETWORK UPLOAD</label>
          <div class="dashboard">
            <ProgressBar :value="pc.loaded ? pc.network.networkUpLoad : 0"
                         :max-value="pc.network.networkUpLoad > 100 ? 500 : 100"

            ></ProgressBar>
          </div>
        </div>
        <div class="col-3">
          <label class="info-label">NETWORK DOWNLOAD</label>
          <div class="dashboard">
            <ProgressBar :value="pc.loaded ? pc.network.networkLoad : 0"
                         :max-value="pc.network.networkLoad > 100 ? 500 : 100"
            ></ProgressBar>

          </div>
        </div>
        <div class="col-3 ">
          <label class="info-label">CPU LOAD</label>
          <div class=" dashboard">
            <ProgressBar :value="pc.loaded ? pc.cpu.cpuLoad : 0" :percent-show="true" :gradient="true"></ProgressBar>

          </div>
        </div>
        <div class="col-3 ">
          <label class="info-label">RAM LOAD</label>
          <div class=" dashboard">
            <ProgressBar :value="pc.loaded ? pc.ram.ramLoad : 0" :percent-show="true"></ProgressBar>

          </div>
        </div>
      </div>

      <div class="accordion mt-3" id="accordionExample "
      >
        <div class="accordion-item"
             style="background-color: #333a43;">
          <div :id="'collapseOne'+[pc.numberPc]" class="accordion-collapse collapse " aria-labelledby="headingOne"
               data-bs-parent="#accordionExample">
            <div class="accordion-body">
              <div class="row text-center">
                <div class="col-4 mx-0">
                  <label class="info-label">IP ADDRESS</label>
                  <div class="info-container">
                    <label>{{ pc.ipv4 }}</label>
                  </div>
                </div>

                <div class="col-4 mx-0">
                  <label class="info-label">MAC ADDRESS</label>
                  <div class="info-container">
                    <label>{{ pc.macAddress }}</label>
                  </div>
                </div>

                <div class="col-4 mx-0">
                  <label class="info-label">OPERATION SYSTEM</label>
                  <div class="info-container">
                    <label>{{ pc.osInfo }}</label>
                  </div>
                </div>

              </div>

              <div class="row my-3">
                <div class="col">
                  <label class="info-label">GPU INFO</label>
                  <div class="info-container">
                    <div v-for="gpu in pc.gpuInfo" :key="gpu">
                      <label>{{ gpu }}</label>
                      <br>
                    </div>

                  </div>
                </div>
              </div>
              <div class="row my-3">
                <div class="col">
                  <label class="info-label">CPU INFO</label>
                  <div class="info-container">
                    <label>{{ pc.cpu.cpuInfo }}</label>
                  </div>
                </div>
              </div>
              <div class="row my-3">
                <div class="col">
                  <label class="info-label">MOTHERBOARD INFO</label>
                  <div class="info-container">
                    <label>{{ pc.motherboardInfo }}</label>
                  </div>
                </div>
              </div>
              <div class="row my-3">
                <div class="col">
                  <label class="info-label">RAM INFO</label>
                  <div class="info-container">
                    <label>{{ pc.ram.ramInfo }}</label>
                  </div>
                </div>
              </div>

              <div class="row my-3 mb-4">
                <div class="col">
                  <label class="info-label">DISK`s INFO</label>
                  <div class="info-container ">
                    <div v-for="disk in pc.disks" :key="disk.id">
                      <label>{{ disk.name }} - {{ (disk.total - disk.loaded).toFixed(2) }}/{{ disk.total.toFixed(2) }}
                        Gb</label>
                    </div>
                  </div>
                </div>
              </div>


            </div>
          </div>
        </div>
      </div>

    </button>
  </div>
</template>

<script>

import ProgressBar from "./UI/ProgressBar.vue"

export default {

  name: "PCInfo",
  data() {
    return {}
  },
  props: {
    pc: {
      numberClassroom: Number,
      numberPc: Number,
      network: {
        networkUpload: Number,
        networkDownload: Number,
      },
      cpu: {
        cpuLoad: Number,
        cpuInfo: String,
      },
      ram: {
        ramLoad: Number,
        ramInfo: String
      },
      ipv4: String,
      macAddress: String,
      osInfo: String,
      gpuInfo: [],
      motherboardInfo: String,
      loaded: Boolean,
      disks: [],
      task: {
        text: '',
        date: '',
        status: 'ENABLED/ DISABLED',
        priority: '1,2,3,4,5',
      }
    },
    numberClassroom: Number,

  },

  components: {
    ProgressBar
  }
}
</script>

<style>
.info-container {
  background-color: rgb(42, 40, 40);
  border-radius: 10px;
  color: #ffff;
  margin-left: 4rem;
  margin-right: 4rem;
  padding: 8px;
  box-shadow: 2px 2px 2px rgba(42, 40, 40, 0.96);
  min-width: 10rem;
}

.btn:active, .btn:focus {
  border: 1px solid black;
}


.main-container {
  background-color: #333a43;
  border-radius: 10px;
  font-family: 'Roboto Condensed', sans-serif;
  padding: 15px;

  /*font-weight: 600;*/

}

.info-label {
  color: #666e6e;
  text-shadow: #ced2d2;
  font-size: 20px;
}

.classroom-label {
  background-color: #7a49eb;
  padding: 6px 2.25rem;
  margin-right: 42%;
  margin-left: 42%;
  font-size: 1.625rem;
  border-radius: 3px;
  box-shadow: 1px 1px 2px #9d4adc;
  color: #ffffff;
  width: 100%;
  position: relative;
  bottom: 40px;
}

.btn-notate {
  position: relative;
  right: 20px;
  color: #2a2828;

}

.dashboard {

  display: flex;
  justify-content: center;
  margin: 0;
  height: 15rem;
  border-radius: 0.25rem;
  background-color: #333A43;
}

.dashboard-disk {

  display: flex;
  justify-content: left;
  margin: 0;
  height: 10rem;

  border-radius: 0.25rem;
  background-color: #2a2828;
}
</style>