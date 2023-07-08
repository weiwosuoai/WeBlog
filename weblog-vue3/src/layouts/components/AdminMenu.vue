<template>
    <div class="meun" :style="{ width: $store.state.menuWidth }">
        <span class="logo">
            <!-- <el-icon class="mr-1">
                <Trophy />
            </el-icon> -->
            <svg t="1682227931603" class="icon mr-1" viewBox="0 0 1024 1024" version="1.1"
                xmlns="http://www.w3.org/2000/svg" p-id="10154" width="30" height="30">
                <path
                    d="M597.333333 565.333333h-170.666666c-17.493333 0-32 14.506667-32 32s14.506667 32 32 32h170.666666c17.493333 0 32-14.506667 32-32s-14.506667-32-32-32zM426.666667 458.666667h85.333333c17.493333 0 32-14.506667 32-32s-14.506667-32-32-32h-85.333333c-17.493333 0-32 14.506667-32 32s14.506667 32 32 32z"
                    fill="#ffffff" p-id="10155"></path>
                <path
                    d="M690.773333 85.333333H333.653333C178.346667 85.333333 85.76 177.92 85.76 333.226667v357.12c0 155.306667 92.586667 247.893333 247.893333 247.893333h357.12c155.306667 0 247.893333-92.586667 247.893334-247.893333V333.226667C938.666667 177.92 846.08 85.333333 690.773333 85.333333zM768 640c0 85.333333-42.666667 128-128 128H384c-85.333333 0-128-42.666667-128-128V384c0-85.333333 42.666667-128 128-128h170.666667c85.333333 0 128 42.666667 128 128v42.666667c0 23.466667 19.2 42.666667 42.666666 42.666666s42.666667 19.2 42.666667 42.666667v128z"
                    fill="#ffffff" p-id="10156"></path>
            </svg>
            <span v-if="$store.state.menuWidth == '250px'">WeBlog</span>
        </span>

        <el-menu :collapse="isCollapse"  class="border-0 admin-el-menu"
        :default-active="defaultActive"
        :collapse-transition="false"
        unique-opened
         @select="handleSelect">
            <template v-for="(item, index) in menus" :index="index">
                <el-menu-item :index="item.path" class="admin-el-menu-item">
                    <el-icon>
                        <component :is="item.icon"></component>
                    </el-icon>
                    <span>{{ item.name }}</span>
                </el-menu-item>
            </template>

        </el-menu>
    </div>
</template>

<script setup>
import { useRouter, useRoute } from 'vue-router';
import { computed, ref } from 'vue';
import { useStore } from 'vuex';

const router = useRouter()
const route = useRoute()
const store = useStore()

const defaultActive = ref(route.path)

// 是否折叠
const isCollapse = computed(() =>  !(store.state.menuWidth == '250px'))

const menus = [{
    'name': '仪表盘',
    'icon': 'Monitor',
    'path': '/admin',
    'child': []
},
{
    'name': '文章管理',
    'icon': 'Document',
    'path': '/admin/article/list',
    'child': []
},
{
    'name': '分类管理',
    'icon': 'FolderOpened',
    'path': '/admin/category/list',
    'child': []
},
{
    'name': '标签管理',
    'icon': 'PriceTag',
    'path': '/admin/tag/list',
    'child': []
},
// {
//     'name': '轮播图管理',
//     'icon': 'Picture',
//     'path': '/admin/carousel/list',
//     'child': []
// },
{
    'name': '博客设置',
    'icon': 'Setting',
    'path': '/admin/blog/setting',
    'child': []
}
]

const handleSelect = (e) => {
    console.log(defaultActive)
    console.log(route.path)

    router.push(e)
}
</script>

<style>
.meun {
    transition: all 0.2s;
    width: 250px;
    top: 0;
    bottom: 0;
    left: 0;
    overflow-y: auto;
    overflow-x: hidden;
    background-color: #001428!important;
    @apply shadow-md fixed bg-light-50;
}

.admin-el-menu {
    background-color: #001428!important;
}

.admin-el-menu-item {
    color: #c0c4cc!important;
}

.el-menu-item.is-active {
    background-color: #ffffff10!important;
}

.el-menu-item.is-active:before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 2px;
    height: 100%;
    background-color: var(--el-color-primary);
}

.el-menu-item:hover {
    background-color: #ffffff10;
}

.meun::-webkit-scrollbar {
    width: 0;
}

.logo {
    height: 64px;
    background-color: #001428;
    color: #fff;
    @apply flex justify-center items-center text-xl font-thin;
}

</style>