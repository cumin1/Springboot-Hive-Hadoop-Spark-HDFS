import { createApp } from 'vue'
import App from './App.vue'
import Main_main_display from "@/layout/Main_main_display.vue";
import DataManagement from "@/layout/DataManagement.vue";

import { createRouter, createWebHistory } from 'vue-router'; // 引入 Vue Router 的函数

const routes = [
    { path: '/', component: Main_main_display },
    { path: '/datamanager', component: DataManagement },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App)

app.use(router); // 挂载路由
app.mount('#app');

