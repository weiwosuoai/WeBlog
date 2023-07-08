<template>
    <el-affix :offset="0">
        <header class="header-container">
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
        </header>
    </el-affix>
</template>

<script setup>
import { useStore } from 'vuex'
import { ref } from 'vue';
import { showModel, showMessage } from '@/composables/util'

const store = useStore()

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
    @apply bg-light-50;
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
    @apply bg-light-50;
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

body {
    @apply bg-light-50;
}

.bg-gray-hover:hover {
    border-bottom: 1px solid #fff !important;
    background-color: #f4f4f5!important;
}
</style>