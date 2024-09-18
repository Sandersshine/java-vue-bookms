import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import axios from 'axios';
axios.defaults.baseURL = "http://localhost:8088"
// 使其可以在 Vue 组件的任何地方通过 this.$axios 访问
// Vue.prototype.$axios = axios

Vue.config.productionTip = false
Vue.use(ElementUI);

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app')
