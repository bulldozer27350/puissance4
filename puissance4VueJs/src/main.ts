import './assets/main.css'

import { createApp } from 'vue'
import HomeComponent from './HomeComponent.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

createApp(HomeComponent).use(VueAxios, axios).mount('#app')
