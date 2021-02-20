/*
 * @Author: your name
 * @Date: 2020-08-12 17:04:28
 * @LastEditTime: 2020-09-08 10:08:57
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \blog-front\src\main.ts
 */
import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from './utils/http'

Vue.use(ElementUI);

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
//axios.defaults.baseURL = '/api'

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
