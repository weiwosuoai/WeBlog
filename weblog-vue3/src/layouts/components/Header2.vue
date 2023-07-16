<template>
    <!-- <header class="header-container bg-light-50">
            <div class="container mx-auto">
                <el-menu class="el-menu" mode="horizontal" :ellipsis="false" @select="handleSelect">
                    <el-menu-item class="title-li">
                        <a href="/" class="flex items-center title">
                            <svg t="1682412971514" class="icon mr-2" viewBox="0 0 1025 1024" version="1.1"
                                xmlns="http://www.w3.org/2000/svg" p-id="5350" width="30" height="30">
                                <path
                                    d="M653.312248 611.118466 370.992731 611.118466c-26.144681 0-47.286367 21.249278-47.286367 47.286367s21.195482 47.340163 47.286367 47.340163l282.319517 0c26.03709 0 47.232572-21.303073 47.232572-47.340163C700.491024 632.367744 679.349337 611.118466 653.312248 611.118466zM439.743558 415.409929l144.602679 0c26.03709 0 47.286367-21.249278 47.286367-47.178776 0-26.090885-21.249278-47.393958-47.286367-47.393958L439.743558 320.837195c-26.090885 0-47.232572 21.303073-47.232572 47.393958C392.457191 394.160651 413.598878 415.409929 439.743558 415.409929zM819.809752 0.107591C619.098221 0 418.332894 0.107591 217.567567 0 198.577706 0 180.01821 1.452482 161.297328 5.594747c-95.272078 20.926504-158.750932 101.888941-159.988232 197.429997C-1.111708 400.831311 0.555957 598.476491 0.609752 796.175466c0 22.378986 0.968322 44.220016 6.186499 66.545206 21.948621 93.012661 98.768794 158.15918 197.860362 160.25721 102.373102 1.990439 204.907591 0.430365 307.226898 0.430365 98.82259 0 197.752771-0.107591 296.575361 0.107591 19.043656 0.053796 37.441765-1.560074 56.216443-5.863725 94.62653-21.84103 159.288889-101.996533 159.342685-199.36664 0.053796-204.853796 0.107591-409.707591 0-614.561387C1023.964205 90.43047 933.426143 0.161387 819.809752 0.107591zM910.670588 497.179301l0 135.780194 0 30.125558c0 137.501655-111.356974 248.858629-248.912424 248.858629L519.845266 911.943683 391.219892 911.943683 362.277837 911.943683c-137.55545 0-248.912424-111.356974-248.912424-248.858629L113.365413 361.022537c0-137.447859 111.356974-248.858629 248.912424-248.858629l68.535645 0 88.977988 0 4.787812 0c0 0.107591 0 0.107591 0 0.107591 135.296034 2.528395 244.178408 112.863252 244.178408 248.751038l0 3.81949 0 20.442343c0 15.062779 12.265406 27.274389 27.166798 27.274389l28.404098 0 13.717888 0c40.077752 0 72.570318 32.492566 72.570318 72.624113L910.670588 497.179301 910.670588 497.179301z"
                                    p-id="5351" fill="#1296db"></path>
                            </svg>
                            {{ $store.state.setting.blogName }}
                        </a>
                    </el-menu-item>
                    <div class="flex-grow"></div>
                    <el-menu-item @click="$router.push('/category')">分类</el-menu-item>
                    <el-menu-item @click="$router.push('/tag')">标签</el-menu-item>
                    <el-menu-item v-if="isLogin == false" @click="$router.push('/login')">登录</el-menu-item>
                    <el-sub-menu index="2" v-else>
                        <template #title>
                            <el-avatar :size="30" :src="$store.state.setting.avatar" class="mr-2"/>
                            {{ $store.state.user.username }}
                        </template>
                        <el-menu-item index="2-1" @click="$router.push('/admin')" class="bg-gray-hover">
                            <el-icon><Monitor /></el-icon>
                            进入后台
                        </el-menu-item>
                        <el-menu-item index="2-2" @click="logout" class="bg-gray-hover">
                            <svg t="1688554592591" class="icon mr-2 ml-1" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2307" 
                            width="18" height="18"><path d="M874.666667 855.744a19.093333 19.093333 0 0 1-19.136 18.922667H168.469333A19.2 19.2 0 0 1 149.333333 855.530667V168.469333A19.2 19.2 0 0 1 168.469333 149.333333h687.061334c10.581333 0 19.136 8.533333 19.136 18.922667V320h42.666666V168.256A61.717333 61.717333 0 0 0 855.530667 106.666667H168.469333A61.866667 61.866667 0 0 0 106.666667 168.469333v687.061334A61.866667 61.866667 0 0 0 168.469333 917.333333h687.061334A61.76 61.76 0 0 0 917.333333 855.744V704h-42.666666v151.744zM851.84 533.333333l-131.797333 131.754667a21.141333 21.141333 0 0 0 0.213333 29.973333 21.141333 21.141333 0 0 0 29.973333 0.192l165.589334-165.589333a20.821333 20.821333 0 0 0 6.122666-14.976 21.44 21.44 0 0 0-6.314666-14.997333l-168.533334-168.533334a21.141333 21.141333 0 0 0-29.952-0.213333 21.141333 21.141333 0 0 0 0.213334 29.973333L847.296 490.666667H469.333333v42.666666h382.506667z" fill="#8a8a8a" p-id="2308"></path></svg>                            退出登录
                        </el-menu-item>
                    </el-sub-menu>
                </el-menu>
            </div>
        </header> -->
    <header class="sticky top-0 z-100">


        <nav class="bg-white border-gray-200 border-b dark:bg-gray-900">
            <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
                <!-- LOGO -->
                <a href="/" class="flex items-center">
                    <img src="https://flowbite.com/docs/images/logo.svg" class="h-8 mr-3" alt="Flowbite Logo" />
                    <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">
                        {{ $store.state.setting.blogName }}
                    </span>
                </a>
                <div class="flex items-center md:order-2">

                    <span class="text-gray-900 hover:text-blue-700" v-if="isLogin == false"
                        @click="$router.push('/login')">登录</span>
                    <button type="button" v-else
                        class="flex mr-3 text-sm rounded-full md:mr-0 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600"
                        id="user-menu-button" aria-expanded="false" data-dropdown-toggle="user-dropdown"
                        data-dropdown-placement="bottom">
                        <span class="sr-only">Open user menu</span>
                        <!-- 用户登录头像 -->
                        <img class="w-8 h-8 rounded-full" :src="$store.state.setting.avatar" alt="user photo">
                    </button>
                    <!-- Dropdown menu -->
                    <div class="z-50 hidden my-4 text-base list-none bg-white divide-y divide-gray-100 rounded-lg shadow dark:bg-gray-700 dark:divide-gray-600"
                        id="user-dropdown">
                        <ul class="py-2" aria-labelledby="user-menu-button">
                            <li>
                                <a @click="$router.push('/admin')"
                                    class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">
                                    <svg class="inline w-3 h-3 mb-2px mr-1 text-gray-700 dark:text-white" aria-hidden="true"
                                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M10 14v4m-4 1h8M1 10h18M2 1h16a1 1 0 0 1 1 1v11a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1Z" />
                                    </svg>
                                    进入后台
                                </a>
                            </li>
                            <li>
                                <a @click="logout"
                                    class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">
                                    <svg class="inline w-3 h-3 mb-2px mr-1 text-gray-700 dark:text-white" aria-hidden="true"
                                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 16 16">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                            stroke-width="2"
                                            d="M4 8h11m0 0-4-4m4 4-4 4m-5 3H3a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h3" />
                                    </svg>
                                    退出登录
                                </a>
                            </li>
                        </ul>
                    </div>
                    <button data-collapse-toggle="navbar-user" type="button"
                        class="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
                        aria-controls="navbar-user" aria-expanded="false">
                        <span class="sr-only">Open main menu</span>
                        <svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                            viewBox="0 0 17 14">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M1 1h15M1 7h15M1 13h15" />
                        </svg>
                    </button>
                </div>
                <div class="items-center justify-between hidden w-full md:flex md:w-auto md:order-1" id="navbar-user">
                    <ul
                        class="flex flex-col font-medium p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                        <li>
                            <a @click="$router.push('/')" :class="[currPath == '/' ? 'text-blue-700' : 'text-gray-900']"
                                class="block py-2 pl-3 pr-4 rounded md:bg-transparent md:p-0 md:dark:text-blue-500"
                                aria-current="page">首页</a>
                        </li>
                        <li>
                            <a @click="$router.push('/category')"
                                :class="[currPath == '/category' ? 'text-blue-700' : 'text-gray-900']"
                                class="block py-2 pl-3 pr-4 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">分类</a>
                        </li>
                        <li>
                            <a @click="$router.push('/tag')"
                                :class="[currPath == '/tag' ? 'text-blue-700' : 'text-gray-900']"
                                class="block py-2 pl-3 pr-4 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">标签</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


    </header>

    
</template>

<script setup>
import { useStore } from 'vuex'
import { useRoute } from 'vue-router';
import { ref } from 'vue';
import { showModel, showMessage } from '@/composables/util'
import { onMounted } from 'vue'
import { initDropdowns, initCollapses } from 'flowbite'


// initialize components based on data attribute selectors
onMounted(() => {
    initCollapses();
    initDropdowns();
})

const store = useStore()
const route = useRoute()

const currPath = ref(route.path)

console.log(store.state.user)
const keys = Object.keys(store.state.user)
console.log('====== keys')
console.log(keys)
const isLogin = ref(keys.length > 0)

function logout() {
    showModel('是否确定要退出登录？').then(() => {
        console.log('登出')
        store.dispatch('logout')

        // 提示登出成功
        showMessage('退出登录成功', 'success')

        isLogin.value = false
    }).catch(() => { })
}

</script>

<style scoped>
.container {
    max-width: 1260px;
}

.header-container {
    border-bottom: solid 1px var(--el-menu-border-color);
}

.el-menu--horizontal {
    border-bottom: 0;
}

.el-menu {
    /* max-width: 1260px; */
    height: 55px;
}

.el-header {
    border-bottom: 1px solid #dcdfe6;
    ;
    height: 55px;
}

.navbar-wrapper {
    height: 55px;
}

.logo-img {
    height: 40px;
}

.logo-container {
    /* display: flex;
    align-items: center; */
}

.logo-container>a {
    height: 28px;
    width: 128px;
}

.title {
    font-size: 1.6rem;
    font-weight: 800;
}

.title-li:hover {
    border-bottom: 1px solid #fff !important;
}

/* body {
    @apply bg-light-50;
} */

.bg-gray-hover:hover {
    border-bottom: 1px solid #fff !important;
    background-color: #f4f4f5 !important;
}</style>