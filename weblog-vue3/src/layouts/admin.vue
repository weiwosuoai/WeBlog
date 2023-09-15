<template>
    <el-container>
        <el-aside :width='$store.state.menuWidth'>
            <AdminMenu></AdminMenu>
        </el-aside>

        <el-container>
            <el-header>
                <AdminHeader></AdminHeader>
            </el-header>

            <el-main>
                <AdminTagList></AdminTagList>
                <router-view v-slot="{ Component }">
                    <Transition name="fade">
                        <keep-alive :max="10">
                            <component :is="Component"></component>
                        </keep-alive>
                    </Transition>
                </router-view>
            </el-main>

            <el-footer>
                <AdminFooter></AdminFooter>
            </el-footer>
        </el-container>
    </el-container>
</template>

<script setup>
import AdminHeader from '@/layouts/components/AdminHeader.vue';
import AdminMenu from '@/layouts/components/AdminMenu.vue';
import AdminTagList from '@/layouts/components/AdminTagList.vue';
import AdminFooter from '@/layouts/components/AdminFooter.vue';
</script>

<style scoped>
.el-aside {
    transition: all 0.3s;
}

.el-header {
    padding: 0;
}

.el-footer {
    padding: 0!important;
}

.fade-enter-from {
    opacity: 0;
}

.fade-enter-to {
    opacity: 1;
}

.fade-leave-from {
  opacity: 1;
}

.fade-leave-to {
  opacity: 0;
}

.fade-leave-active {
    transition: all 0.3s;
}

.fade-enter-active {
    transition: all 0.3s;
    transition-delay: 0.3s;
}
</style>