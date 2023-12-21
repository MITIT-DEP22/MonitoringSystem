import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router/router";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import components from '@/components/UI'
import axios from "axios";
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import VueChartkick from 'vue-chartkick'
import 'chartkick/chart.js'
import 'bootstrap/dist/js/bootstrap'

//base url for axios`es requests
axios.defaults.baseURL = 'http://localhost:8080/api/v1'

const app = createApp(App);

components.forEach(component => {
    app.component(component.name, component);
});

app.component('fas', FontAwesomeIcon);
library.add(fas);

app.use(VueChartkick)

app
    .use(router)
    .mount('#app');
