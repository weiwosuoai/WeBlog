<template>
    <el-drawer v-model="showDrawer" 
    :title="title" 
    :size="size" 
    :destroy-on-close="destoryOnClose"
    :close-on-click-modal="false">
        <div class="formDrawer">
            <div class="body">
                <slot></slot>
            </div>
            <div class="footer">  
                <el-button type="primary" @click="submit" :loading="loading">{{ confirmText }}</el-button>
                <el-button type="default" @click="close">取消</el-button>
            </div>
        </div>
    </el-drawer>
</template>

<script setup>
    import { ref } from 'vue';

    const showDrawer = ref(false)

    const props = defineProps({
        title: String,
        size: {
            type: String,
            default: '45%'
        },
        destoryOnClose: {
            type: Boolean,
            default: false
        },
        confirmText: {
            type: String,
            default: '提交'
        }
    })

    const loading = ref(false)
    const showLoading = () => loading.value = true
    const hideLoading = () => loading.value = false

    const emit = defineEmits(['submit'])
    // 提交
    const submit = () => emit('submit')

    // 打开
    const open = () => showDrawer.value = true
    // 关闭
    const close = () => showDrawer.value = false
    // 向父组件暴露下面的方法
    defineExpose({
        open,
        close,
        showLoading,
        hideLoading
    })
</script>

<style>
    .formDrawer {
        width: 100%;
        height: 100%;
        position: relative;
        @apply flex flex-col;
    }

    .formDrawer .body {
        flex: 1;
        overflow-y: auto;
    }

    .formDrawer .footer {
        @apply mt-auto mt-2;
    }
</style>