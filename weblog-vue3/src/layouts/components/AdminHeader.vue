<template>
    <el-affix :offset="0">
        <div class="header flex text-light-50 top-0 right-0 left-0 items-center">
            <!-- 展开、收缩侧边栏 -->
            <el-icon class="icon-btn" @click="$store.commit('HANDLE_MENU_WIDTH')">
                <Fold v-if="$store.state.menuWidth == '250px'" />
                <Expand v-else />
            </el-icon>

            <div class="ml-auto flex justify-center items-center">
                <!-- 跳转博客前台 -->
                <el-tooltip class="box-item" effect="dark" content="跳转博客前台" placement="bottom">
                    <el-icon class="icon-btn" @click="router.push('/')">
                        <House />
                    </el-icon>
                </el-tooltip>

                <!-- 刷新 -->
                <el-tooltip class="box-item" effect="dark" content="刷新" placement="bottom">
                    <el-icon class="icon-btn" @click="refresh">
                        <Refresh />
                    </el-icon>
                </el-tooltip>

                <!-- 全屏 -->
                <el-tooltip class="box-item" effect="dark" content="全屏" placement="bottom">
                    <el-icon class="icon-btn" @click="toggle">
                        <FullScreen v-if="!isFullscreen" />
                        <Aim v-else />
                    </el-icon>
                </el-tooltip>

                <el-dropdown class="dropdown flex justify-center items-center text-light-50 mx-5" @command="handleCommand">
                    <span class="flex justify-center items-center">
                        <el-avatar :size="25" :src="$store.state.user.avatar" class="mr-2" />
                        {{ $store.state.user.username }}
                        <el-icon class="el-icon--right">
                            <arrow-down />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="updatePassword">修改密码</el-dropdown-item>
                            <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </div>
    </el-affix>


    <!-- 修改密码 -->
    <el-dialog v-model="dialogShow" title="修改密码" width="30%" :show-close="false">
        <el-form ref="formRef" :rules="rules" :model="form">
            <el-form-item label="用户名" prop="oldPassword" label-width="120px">
                <el-input v-model="form.username" disabled />
            </el-form-item>
            <el-form-item label="新密码" prop="newPassword" label-width="120px">
                <el-input v-model="form.newPassword" type="password" autocomplete="off" placeholder="请输入新密码"
                    show-password />
            </el-form-item>
            <el-form-item label="确认密码" prop="rePassword" label-width="120px">
                <el-input v-model="form.rePassword" type="password" autocomplete="off" placeholder="请再次确认新密码"
                    show-password />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogShow = false">取消</el-button>
                <el-button type="primary" @click="onSubmit">
                    提交
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>



<script setup>
import { showModel, showMessage } from '@/composables/util'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ref, reactive } from 'vue'
import { useFullscreen } from '@vueuse/core'
import { updateAdminPassword } from '@/api/admin/user'
import { times } from 'lodash'

const { isFullscreen, toggle } = useFullscreen()

const store = useStore()
const router = useRouter()

const dialogShow = ref(false)

const form = reactive({
    username: store.state.user.username,
    newPassword: '',
    rePassword: '',
})

const rules = {
    username: [
        {
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
        }
    ],
    newPassword: [
        {
            required: true,
            message: '新密码不能为空',
            trigger: 'blur',
        },
    ],
    rePassword: [
        {
            required: true,
            message: '请再次输入密码',
            trigger: 'blur',
        },
    ]
}


const formRef = ref(null)

const onSubmit = () => {
    // 登录表单验证
    formRef.value.validate((valid) => {
        if (!valid) {
            console.log('验证不通过')
            return false
        }

        console.log("newPassword:" + form.newPassword)
        console.log("rePassword:" + form.rePassword)

        // 校验两次输入的密码是否一致
        if (form.newPassword != form.rePassword) {
            showMessage("两次输入的密码不一致！", 'warning')
            return
        }

        updateAdminPassword(form).then(res => {
            if (res.success) {
                // 提示
                showMessage('重置密码成功, 请重新登录！', 'success', 'message')
                store.dispatch('logout')
                router.push('/login')
                dialogShow.value = false
            } else {
                let message = res.message
                showMessage(message, 'warning')
            }
        })
    })
}

const handleCommand = (e) => {
    console.log(e)
    switch (e) {
        case 'updatePassword':
            dialogShow.value = true
            break
        case 'logout':
            logout()
            break
    }

}

// 刷新页面
const refresh = () => location.reload()

function logout() {
    showModel('是否确定要退出登录？').then(() => {
        console.log('登出')
        store.dispatch('logout')

        // 跳转回登录页
        router.push('/login')

        // 提示登出成功
        showMessage('退出登录成功', 'success')
    }).catch(() => { })
}
</script>

<style>
.header {
    height: 64px;
    background-color: #fff;
    z-index: 100;
}



.icon-btn {
    @apply flex justify-center items-center;
    width: 42px;
    height: 64px;
    cursor: pointer;
    color: #374151;
}

.icon-btn:hover {
    @apply bg-gray-200;
}

.header .dropdown {
    height: 64px;
    cursor: pointer;
    color: #374151 !important;
}
</style>