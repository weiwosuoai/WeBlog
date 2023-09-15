<template>
    <div class="tag-list" :style="{ left: $store.state.menuWidth }">
        <el-tabs v-model="activeTab" type="card" @tab-remove="removeTab" @tab-change="tabChange" style="min-width: 100px;">
            <el-tab-pane v-for="item in tabList" :key="item.path" :label="item.title" :name="item.path"
                :closable="item.path != '/admin'">
            </el-tab-pane>
        </el-tabs>

        <span class="tag-dropdown-btn">
            <el-dropdown @command="handleClose">
                <span class="el-dropdown-link">
                    <el-icon>
                        <arrow-down />
                    </el-icon>
                </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item command="closeOther">关闭其他</el-dropdown-item>
                        <el-dropdown-item command="closeAll">关闭全部</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </span>
    </div>
    <div style="height: 44px;"></div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter, onBeforeRouteUpdate } from 'vue-router';
import { useCookies } from '@vueuse/integrations/useCookies'

const route = useRoute()
const router = useRouter()
const cookie = useCookies()

const activeTab = ref(route.path)
const tabList = ref([
    {
        title: '仪表盘',
        path: '/admin'
    }
])

const handleClose = (e) => {
    if (e == 'closeOther') {
        tabList.value = tabList.value.filter(tab => tab.path == '/admin' || tab.path == activeTab.value)
    } else if (e = 'closeAll') {
        // 切换回后台首页
        activeTab.value = '/admin'
        tabList.value = [{
            title: '仪表盘',
            path: '/admin'
        }]
    }
    cookie.set('tabList', tabList.value)
}

const tabChange = (e) => {
    activeTab.value = e
    router.push(e)
}

// 初始化 tab
function initTabList() {
    let tabs = cookie.get('tabList')
    if (tabs) {
        console.log(tabs)
        tabList.value = tabs
    }
}
initTabList()

// 添加标签导航
function addTab(tab) {
    let noTab = tabList.value.findIndex(i => i.path == tab.path) == -1
    if (noTab) {
        tabList.value.push(tab)
    }

    cookie.set('tabList', tabList.value)
}

onBeforeRouteUpdate((to, from) => {
    activeTab.value = to.path
    // console.log(to)
    addTab({
        title: to.meta.title,
        path: to.path
    })
})

const removeTab = (t) => {
    let tabs = tabList.value
    let currActiveTab = activeTab.value

    // 如果想要关闭的 tab 等于现在处理 active 的 tab
    if (currActiveTab == t) {
        tabs.forEach((tab, index) => {
            if (tab.path == t) {
                const nextTab = tabs[index + 1] || tabs[index - 1]
                if (nextTab) {
                    currActiveTab = nextTab.path
                }
            }
        })
    }

    activeTab.value = currActiveTab
    // 删除当前 tab
    tabList.value = tabList.value.filter(tab => tab.path != t)
    cookie.set('tabList', tabList.value)
}
</script>

<style scoped>
.tag-list {
    @apply fixed flex items-center px-2;
    top: 64px;
    right: 0;
    height: 44px;
    z-index: 100;
    transition: all 0.3s;
    background-color: #fff;
    border-bottom: 1px solid #d8dce5;
    box-shadow: 0 1px 3px #00000010, 0 0 3px #00000010;
}

:deep(.el-tabs__item) {
    font-size: 12px;
    border: 1px solid #d8dce5!important;
    border-radius: 3px!important;
}

:deep(.el-tabs--card>.el-tabs__header .el-tabs__item) {
    margin-left: 0.1rem!important;
    margin-right: 0.1rem!important;
}

:deep(.el-tabs__item.is-active) {
    background-color: var(--el-color-primary)!important;
    color: #fff;
}

:deep(.el-tabs__item.is-active::before) {
    content: "";
    background-color: #fff;
    display: inline-block;
    width: 8px;
    height: 8px;
    border-radius: 50%;
    position: relative;
    margin-right: 4px;
}

.tag-dropdown-btn {
    @apply bg-white rounded flex items-center ml-auto justify-center px-2;
    width: 32px;
    height: 32px;
}

:deep(.el-tabs) {
    height: 32px;
}

:deep(.el-tabs__header) {
    @apply mb-0;
}

:deep(.el-tabs--card>.el-tabs__header .el-tabs__nav) {
    @apply border-0;
}

:deep(.el-tabs--card>.el-tabs__header .el-tabs__item) {
    @apply border-0 bg-white mx-1 rounded;
    height: 32px;
    line-height: 32px;
}

:deep(.el-tabs--card>.el-tabs__header) {
    @apply border-0;
}

:deep(.el-tabs__nav-prev),
:deep(.el-tabs__nav-next) {
    height: 32px;
    line-height: 32px;
}

:deep(.is-disabled) {
    cursor: not-allowed;
    @apply text-gray-300;
}
</style>