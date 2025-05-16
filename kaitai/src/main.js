import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import axios from './axios.js'
import VueAxios from 'vue-axios'
import Vueconfig from '../vue.config.js'
import globle from './globalFun.js'


Vue.use(VueAxios, axios)
Vue.prototype.$axios = axios 
Vue.use(Element)
Vue.config.productionTip = false


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
