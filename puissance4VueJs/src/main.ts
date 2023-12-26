import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
/* import TestVue from './Test.vue' */
import axios from 'axios'
import VueAxios from 'vue-axios'

createApp(App).use(VueAxios, axios).mount('#app')
