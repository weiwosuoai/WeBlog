import { createApp } from 'vue'
// import ElementPlus from 'element-plus'
// import 'element-plus/dist/index.css'
import 'virtual:windi.css'
import App from './App.vue'
import router from './router'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import store from './store'
import "@/permission"

import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import VueHighlightJS from 'vue3-highlightjs'
import 'vue3-highlightjs/styles/monokai-sublime.css'

// import './assets/main.css'

const app = createApp(App)

app.use(store)
app.use(router)
// app.use(ElementPlus, { locale: zhCn })

app.use(VueHighlightJS)

// 引入图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.mount('#app')
