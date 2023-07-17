import axios from "axios";
import { notification, showMessage } from '@/composables/util'
import { getToken } from '@/composables/auth'
import store from "@/store";
import { Console } from "windicss/utils";

const instance = axios.create({
    baseURL: import.meta.env.VITE_APP_BASE_API,
    // baseURL: '/',
    timeout: 7000
});

// 添加请求拦截器
instance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    const token = getToken()
    console.log('统一添加 token: ' + token)

    // 统一添加请求头 Token
    if (token) {
        config.headers['Authorization'] = 'Bearer ' + token
    }

    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response.data;
}, function (error) {
    // 对响应错误做点什么
    let status = error.response.status
    console.log('错误响应==========》' + status)
    if (status == 401 || status == 402) {
        console.log('401-------------')
        store.dispatch('logout').finally(() => location.reload())
    }

    let isSuccess = error.response.data.success
    console.log('错误响应==========》' + isSuccess)
    if (!isSuccess) {
        console.log('error: ' + error.response.data.message)
        let message = error.response.data.message || '请求失败'

        // todo 失效的情况
        // notification(message, 'error')
        showMessage(messsage, 'error')
    }

    return Promise.reject(error);
});

// 暴露
export default instance