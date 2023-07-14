<template>
    
    <el-card shadow="never" :body-style="{ padding: '20px' }" class="mb-5 border-1">
        <!-- card body -->
        <el-text class="mx-1 mr-3">文章标题</el-text>
        <el-input v-model="searchTitle" placeholder="请输入（模糊查询）" class="w-50 mr-5" />

        <el-text class="mx-1 mr-3">发布日期</el-text>
        <el-date-picker style="top: 3px" v-model="pickDate" type="daterange" range-separator="至" start-placeholder="开始时间"
            end-placeholder="结束时间" :shortcuts="shortcuts" size="default" @change="datepickerChange" />

            <el-button type="primary" class="ml-3" :icon="Search" @click="getTableData">查询</el-button>
            <el-button class="ml-3" :icon="RefreshRight" @click="reset">重置</el-button>
    </el-card>


    <el-card shadow="never" class="border-1">
        <!-- card body -->
        <!-- 新增按钮 -->
        <div>
            <el-button type="primary" @click="isArticlePublishEditorShow = true">
                <el-icon class="mr-1">
                    <EditPen />
                </el-icon>
                写文章</el-button>
        </div>

        <el-table :data="tableData" stripe style="width: 100%" class="mt-4" v-loading="tableLoading">
            <el-table-column prop="title" label="标题" width="380" />
            <el-table-column label="预览图" width="180">
                <template #default="scope">
                    <el-image style="width: 50px;" :src="scope.row.titleImage" />
                </template>
            </el-table-column>
            <el-table-column prop="createTime" label="发布时间" width="180" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button size="small" @click="showArticleUpdateEditorShow(scope.row)">
                        <el-icon class="mr-1">
                            <Edit />
                        </el-icon>
                        编辑</el-button>
                    <el-button size="small" @click="previewArticle(scope.row)">
                        <el-icon class="mr-1">
                            <View />
                        </el-icon>
                        预览</el-button>
                    <el-button type="danger" size="small" @click="deleteArticleSubmit(scope.row)">
                        <el-icon class="mr-1">
                            <Delete />
                        </el-icon>
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <div class="mt-5 flex item-center justify-center">
            <el-pagination v-model:current-page="current" v-model:page-size="size" :page-sizes="[10, 20, 50]" :small="small"
                :disabled="disabled" background="true" layout="total, sizes, prev, pager, next, jumper" :total="total"
                @size-change="handleSizeChange" @current-change="getTableData" />
        </div>
    </el-card>

    <!-- 写博客 -->
    <el-dialog v-model="isArticlePublishEditorShow" fullscreen="true" :show-close="false" :modal="false">

        <template #header="{ close, titleId, titleClass }">
            <div class="">
                <div class="my-header flex justify-between">
                        <h4 class="font-bold">写文章</h4>
                        <div>
                            <el-button @click="isArticlePublishEditorShow = false">取消</el-button>
                            <el-button type="primary" @click="onSubmit">
                                <el-icon class="mr-1">
                                    <Promotion />
                                </el-icon>
                                发布
                            </el-button>
                        </div>
                    </div>
            </div>
        </template>
        <el-form :model="form" ref="publishArticleFormRef" label-position="top" :size="large" :rules="rules">
            <el-form-item label="标题" prop="title">
                <el-input v-model="form.title" autocomplete="off" size="large" maxlength="40" show-word-limit clearable />
            </el-form-item>
            <el-form-item label="内容" prop="content">
                <!-- <MDEditor :content="form.content" @event="handleMd"></MDEditor> -->
                <MdEditor v-model="form.content" @onUploadImg="onUploadImg" editorId="publishArticleEditor" />
            </el-form-item>
            <el-form-item label="封面" prop="titleImage">
                <el-upload class="avatar-uploader" action="#" :on-change="handleTitleImageChange" :auto-upload="false"
                    :show-file-list="false" :on-success="handleAvatarSuccess">
                    <img v-if="form.titleImage" :src="form.titleImage" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
            </el-form-item>
            <el-form-item label="摘要" prop="description">
                <el-input v-model="form.description" :rows="3" type="textarea" placeholder="请输入文章摘要" />
            </el-form-item>
            <el-form-item label="分类" prop="categoryId">
                <el-select v-model="form.categoryId" clearable placeholder="---请选择---" size="large">
                    <el-option v-for="item in categories" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="标签" prop="tags">
                <!-- 标签选择 -->
                <el-select v-model="form.tags" multiple filterable remote reserve-keyword placeholder="---请输入---"
                    remote-show-suffix :remote-method="remoteMethod" allow-create default-first-option
                    :loading="tagSelectLoading" size="large">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>

        </el-form>
    </el-dialog>

    <!-- 编辑博客 -->
    <el-dialog v-model="isArticleUpdateEditorShow" fullscreen="true" :show-close="false" :modal="false">
        <template #header="{ close, titleId, titleClass }">
            <div class="my-header flex items-center justify-between">
                <h4 class="font-bold">编辑文章</h4>
                <div>
                    <el-button @click="hideArticleUpdateEditor">取消</el-button>
                    <el-button type="primary" @click="updateSubmit">
                        <el-icon class="mr-1">
                            <Promotion />
                        </el-icon>
                        提交
                    </el-button>
                </div>
            </div>
        </template>
        <el-form :model="form" ref="updateArticleFormRef" label-position="top" :size="large" :rules="rules">
            <el-form-item label="标题" prop="title">
                <el-input v-model="form.title" autocomplete="off" size="large" maxlength="40" show-word-limit clearable />
            </el-form-item>
            <el-form-item label="内容" prop="content">
                <!-- <MDEditor :content="form.content" @event="handleMd"></MDEditor> -->
                <MdEditor v-model="form.content" @onUploadImg="onUploadImg" editorId="updateArticleEditor" />
            </el-form-item>
            <el-form-item label="封面" prop="titleImage">
                <el-upload class="avatar-uploader" action="#" :on-change="handleTitleImageChange" :auto-upload="false"
                    :show-file-list="false" :on-success="handleAvatarSuccess">
                    <img v-if="form.titleImage" :src="form.titleImage" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
            </el-form-item>
            <el-form-item label="摘要" prop="description">
                <el-input v-model="form.description" :rows="3" type="textarea" placeholder="请输入文章摘要" />
            </el-form-item>
            <el-form-item label="分类" prop="categoryId">
                <el-select v-model="form.categoryId" clearable placeholder="---请选择---" size="large">
                    <el-option v-for="item in categories" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="标签" prop="tags">
                <!-- 标签选择 -->
                <el-select v-model="form.tags" multiple filterable remote reserve-keyword placeholder="---请输入---"
                    remote-show-suffix :remote-method="remoteMethod" allow-create default-first-option
                    :loading="tagSelectLoading" size="large">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>
        </el-form>
    </el-dialog>
</template>

<script setup>
// import { ElMessage, ElMessageBox } from 'element-plus'
import { ref, reactive } from 'vue';
// import MDEditor from '@/components/MDEditor.vue'
import { publishArticle, getArticlePageList, deleteArticle, getArticleDetail, updateArticle } from '@/api/admin/article'
import { uploadFile } from '@/api/admin/file'
import MdEditor from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
import { showMessage } from '@/composables/util'
import { useRouter } from 'vue-router'
import { getCategorySelect } from '@/api/admin/category'
import { selectTags, getTagSelect } from '@/api/admin/tag'
import moment from 'moment';
import { Search, RefreshRight } from '@element-plus/icons-vue'

const router = useRouter()

const isArticlePublishEditorShow = ref(false)
const isArticleUpdateEditorShow = ref(false)
const tableLoading = ref(false)

const searchTitle = ref('')
const pickDate = ref('')
const startDate = reactive({})
const endDate = reactive({})

const reset = () => {
    pickDate.value = ''
    startDate.value = null
    endDate.value = null
    searchTitle.value = ''
}

const datepickerChange = (e) => {
    startDate.value = moment(e[0]).format('YYYY-MM-DD HH:mm:ss')
    endDate.value = moment(e[1]).format('YYYY-MM-DD HH:mm:ss')
}

const shortcuts = [
    {
        text: '最近一周',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            return [start, end]
        },
    },
    {
        text: '最近一个月',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            return [start, end]
        },
    },
    {
        text: '最近三个月',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            return [start, end]
        },
    },
]

const handleTitleImageChange = (file) => {
    console.log('开始上传文件')
    console.log(file)
    let formData = new FormData()
    formData.append("file", file.raw);
    uploadFile(formData).then((e) => {
        if (e.success == false) {
            let message = e.message
            showMessage(message, 'warning', 'message')
            return
        }
        form.titleImage = e.data.url
        showMessage('文章题图上传成功', 'success', 'message')
    })
}

const hideArticleUpdateEditor = () => {
    isArticleUpdateEditorShow.value = false
    form.title = ''
    form.content = '请输入内容'
    form.titleImage = ''
    form.categoryId = null
    form.tags = []
}

const showArticleUpdateEditorShow = (row) => {
    isArticleUpdateEditorShow.value = true
    let articleId = row.id
    getArticleDetail(articleId).then((e) => {
        if (e.success == true) {
            form.id = e.data.id
            form.title = e.data.title
            form.content = e.data.content
            form.titleImage = e.data.titleImage
            form.categoryId = e.data.categoryId
            form.tags = e.data.tagIds
            form.description = e.data.description
        }
    })
}

const onUploadImg = async (files, callback) => {
    const res = await Promise.all(
        files.map((file) => {
            return new Promise((rev, rej) => {
                console.log('==> 开始上传文件...')
                let formData = new FormData()
                formData.append("file", file);
                uploadFile(formData).then((res) => {
                    console.log(res)
                    console.log('访问路径：' + res.data.url)
                    callback([res.data.url]);
                })
            });
        })
    );
}

const previewArticle = (row) => {
    // 打开一个新页面
    let routeData = router.resolve({ path: '/article/detail', query: { articleId: row.id } });
    window.open(routeData.href, '_blank');
}

const form = reactive({
    id: null,
    title: '',
    content: '请输入内容',
    titleImage: '',
    categoryId: null,
    tags: [],
    description: ""
})


const publishArticleFormRef = ref(null)
const updateArticleFormRef = ref(null)
const rules = {
    title: [
        { required: true, message: '请输入文章标题', trigger: 'blur' },
        { min: 1, max: 40, message: '文章标题要求大于1个字符，小于40个字符', trigger: 'blur' },
    ],
    content: [{ required: true }],
    titleImage: [{ required: true }],
    categoryId: [{ required: true, message: '请选择文章分类', trigger: 'blur' }],
    tags: [{ required: true, message: '请选择文章标签', trigger: 'blur' }],
    description: [{ required: true, message: '请输入文章摘要', trigger: 'blur' }],
}


// const handleMd = (md) => {
//     form.content = md
//     console.log('子组件回传过来的数据：' + form.content)
// }

const tableData = ref([])
// 当前页码
const current = ref(1)
const total = ref(0)
const size = ref(10)

// 获取分页数据
function getTableData() {
    console.log('获取分页数据')
    tableLoading.value = true
    getArticlePageList({ current: current.value, size: size.value, startDate: startDate.value, endDate: endDate.value, searchTitle: searchTitle.value })
        .then((res) => {
            if (res.success == true) {
                tableData.value = res.data.records
                current.value = res.data.current
                total.value = res.data.total
                size.value = res.data.size
            }
        }).finally(() => {
            tableLoading.value = false
        })
}
getTableData()

const handleSizeChange = (e) => {
    console.log('选择的页码' + e)
    size.value = e
    getTableData()
}


const onSubmit = () => {
    isArticlePublishEditorShow.value = true
    console.log('提交内容' + form.content)
    publishArticleFormRef.value.validate((valid) => {
        if (!valid) {
            return false
        }
        publishArticle(form).then((e) => {
        console.log(e)
        if (e.success == false) {
            var message = e.message
            showMessage(message, 'warning', 'message')
            return
        }

        showMessage('发布成功', 'success', 'message')
        isArticlePublishEditorShow.value = false
        location.reload()
    })
    })
}

const updateSubmit = () => {
    isArticleUpdateEditorShow.value = true
    console.log('提交内容' + form.content)
    updateArticleFormRef.value.validate((valid) => {
        if (!valid) {
            return false
        }
        updateArticle(form).then((e) => {
        console.log(e)
        if (e.success == false) {
            var message = e.message
            showMessage(message, 'warning', 'message')
            return
        }

        showMessage('修改成功', 'success', 'message')
        isArticleUpdateEditorShow.value = false
        location.reload()
    })
    })
}

const deleteArticleSubmit = (row) => {
    console.log(row.id)
    ElMessageBox.confirm(
        '是否确认要删除该文章?',
        '提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            deleteArticle(row.id).then((e) => {
                if (e.success == true) {
                    showMessage('删除成功', 'success')
                    location.reload()
                } else {
                    let message = e.message
                    showMessage(message, 'warning')
                }
            })

        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '删除失败',
            })
        })
}

// 文章分类
const categories = ref([])
getCategorySelect().then((e) => {
    console.log('获取分类数据')
    console.log(e)
    categories.value = e.data
})

// 文章标签
const tagSelectLoading = ref(false)
const options = ref([])
getTagSelect().then((e) => {
    console.log('获取标签数据')
    console.log(e)
    options.value = e.data
})

const remoteMethod = (query) => {
    console.log('远程搜索')
    console.log(options.value)
    if (query) {
        tagSelectLoading.value = true
        setTimeout(() => {
            tagSelectLoading.value = false
            selectTags(query).then((e) => {
                if (e.success) {
                    options.value = e.data
                }
            })
            //   options.value = list.value.filter((item) => {
            //     return item.label.toLowerCase().includes(query.toLowerCase())
            //   })
        }, 200)
    }
}
</script>


<style scoped>
.avatar-uploader .avatar {
    width: 278px;
    display: block;
}

.message {
    z-index: 9999 !important;
}
</style>

<style>
.w-50 {
    width: 12.5rem!important;
}

.mr-3 {
    margin-right: 0.75rem!important;
}

.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}

.el-select--large {
    width: 600px;
}


</style>