<template>
    <el-card shadow="never" :body-style="{ padding: '20px' }">
        <el-form :model="form" label-width="160px" :rules="rules">
            <el-form-item label="博客名称" prop="blogName">
                <el-input v-model="form.blogName" clearable />
            </el-form-item>
            <el-form-item label="作者名" prop="author">
                <el-input v-model="form.author" clearable />
            </el-form-item>
            <el-form-item label="作者头像" prop="avatar">
                <el-upload class="avatar-uploader" action="#" :on-change="handleTitleImageChange" :auto-upload="false"
                    :show-file-list="false" :on-success="handleAvatarSuccess">
                    <img v-if="form.avatar" :src="form.avatar" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
            </el-form-item>
            <el-form-item label="介绍语">
                <el-input v-model="form.introduction" type="textarea" />
            </el-form-item>
            <el-form-item label="开启 GihHub 访问">
                <el-switch v-model="isGithubCheck" inline-prompt :active-icon="Check" :inactive-icon="Close" @change="githubSwitchChange"/>
            </el-form-item>
            <el-form-item label="GitHub 主页访问地址" v-if="isGithubCheck">
                <el-input v-model="form.githubHome" clearable placeholder="请输入 GitHub 主页访问的 URL" />
            </el-form-item>
            <el-form-item label="开启 CSDN 访问">
                <el-switch v-model="isCSDNCheck" inline-prompt :active-icon="Check" :inactive-icon="Close" @change="csdnSwitchChange"/>
            </el-form-item>
            <el-form-item label="CSDN 主页访问地址" v-if="isCSDNCheck">
                <el-input v-model="form.csdnHome" clearable placeholder="请输入 CSDN 主页访问的 URL" />
            </el-form-item>
            <el-form-item label="开启 Gitee 访问">
                <el-switch v-model="isGiteeCheck" inline-prompt :active-icon="Check" :inactive-icon="Close" @change="giteeSwitchChange"/>
            </el-form-item>
            <el-form-item label="Gitee 主页访问地址" v-if="isGiteeCheck">
                <el-input v-model="form.giteeHome" clearable placeholder="请输入 Gitee 主页访问的 URL" />
            </el-form-item>
            <el-form-item label="开启知乎访问">
                <el-switch v-model="isZhihuCheck" inline-prompt :active-icon="Check" :inactive-icon="Close" @change="zhihuSwitchChange"/>
            </el-form-item>
            <el-form-item label="知乎主页访问地址" v-if="isZhihuCheck">
                <el-input v-model="form.zhihuHome" clearable placeholder="请输入知乎主页访问的 URL" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">保存</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { Check, Close } from '@element-plus/icons-vue'
import { uploadFile } from '@/api/admin/file'
import { showMessage } from '@/composables/util'
import { getBlogSettingDetail, updateBlogSetting } from '@/api/admin/blogsetting'

const isGithubCheck = ref(false)
const isCSDNCheck = ref(false)
const isGiteeCheck = ref(false)
const isZhihuCheck = ref(false)


const form = reactive({
    blogName: '',
    author: '',
    avatar: '',
    introduction: '',
    githubHome: '',
    giteeHome: '',
    csdnHome: '',
    zhihuHome: '',
})

const rules = {
    blogName: [{ required: true, message: '请输入博客名称', trigger: 'blur' }],
    author: [{ required: true, message: '请输入作者名称', trigger: 'blur' }],
    avatar: [{ required: true, message: '请选择作者头像', trigger: 'blur' }],
}

const githubSwitchChange = (e) => {
    if (e == false) {
        form.githubHome = ''
    }
}

const csdnSwitchChange = (e) => {
    if (e == false) {
        form.csdnHome = ''
    }
}

const giteeSwitchChange = (e) => {
    if (e == false) {
        form.giteeHome = ''
    }
}

const zhihuSwitchChange = (e) => {
    if (e == false) {
        form.zhihuHome = ''
    }
}

const handleTitleImageChange = (file) => {
    console.log('开始上传文件')
    console.log(file)
    let formData = new FormData()
    formData.append("file", file.raw);
    uploadFile(formData).then((e) => {
        if (e.success == false) {
            let message = e.message
            showMessage(message, 'error', 'message')
            return
        }
        form.avatar = e.data.url
        showMessage('头像上传成功', 'success', 'message')
    })
}

function initBlogSetting() {
    getBlogSettingDetail().then((e) => {
        if (e.success == true) {
            form.blogName = e.data.blogName
            form.author = e.data.author
            form.avatar = e.data.avatar
            form.introduction = e.data.introduction
            if (e.data.githubHome) {
                isGithubCheck.value = true
                form.githubHome = e.data.githubHome
            }
            if (e.data.giteeHome) {
                isGiteeCheck.value = true
                form.giteeHome = e.data.giteeHome
            }
            if (e.data.csdnHome) {
                isCSDNCheck.value = true
                form.csdnHome = e.data.csdnHome
            }
            if (e.data.zhihuHome) {
                isZhihuCheck.value = true
                form.zhihuHome = e.data.zhihuHome
            }
        }
})
}
initBlogSetting()


const onSubmit = () => {
    console.log('提交内容' + form.content)
    updateBlogSetting(form).then((e) => {
        console.log(e)
        if (e.success == false) {
            var message = e.message
            showMessage(message, 'warning', 'message')
            return
        }

        showMessage('更新成功', 'success', 'message')
        initBlogSetting()
    })
}
</script>