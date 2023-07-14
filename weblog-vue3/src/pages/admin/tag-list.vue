<template>
    <el-card shadow="never" :body-style="{ padding: '20px' }" class="mb-5 border-1">
        <!-- card body -->
        <el-text class="mx-1 mr-3">标签名称</el-text>
        <el-input v-model="searchTagName" placeholder="请输入（模糊查询）" class="w-50 mr-5" />

        <el-text class="mx-1 mr-3">创建日期</el-text>
        <el-date-picker style="top: 3px" v-model="pickDate" type="daterange" range-separator="至" start-placeholder="开始时间"
            end-placeholder="结束时间" :shortcuts="shortcuts" size="default" @change="datepickerChange" />

            <el-button type="primary" class="ml-3" :icon="Search" @click="getTableData">查询</el-button>
            <el-button class="ml-3" :icon="RefreshRight" @click="reset">重置</el-button>
    </el-card>


    <el-card shadow="never" class="border-1">
        <!-- card body -->
        <!-- 新增按钮 -->
        <div>
            <el-button type="primary" @click="isTagPublishDialogShow = true">
                <el-icon class="mr-1">
                    <Plus />
                </el-icon>
                新增</el-button>
        </div>

        <el-table :data="tableData" stripe style="width: 100%" class="mt-4" v-loading="tableLoading">
            <!-- <el-table-column prop="id" label="ID" width="180" /> -->
            <el-table-column prop="name" label="名称" width="180">
                <template #default="scope">
                    <el-tag class="ml-2" type="info">{{scope.row.name}}</el-tag>
                </template>
                
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="180" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="danger" size="small" @click="deleteTagSubmit(scope.row)">
                        <el-icon class="mr-1">
                            <Delete />
                        </el-icon>
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <div class="mt-5 flex item-center justify-center">
            <el-pagination v-model:current-page="current" v-model:page-size="size" :page-sizes="[10, 20, 50]"
                :small="small" :disabled="disabled" background="true" layout="total, sizes, prev, pager, next, jumper"
                :total="total" @size-change="handleSizeChange" @current-change="getTableData" />
        </div>
    </el-card>

    <!-- 新增标签 -->
    <el-dialog v-model="isTagPublishDialogShow" title="新增标签" width="30%" :show-close="false" draggable>
        <el-form :model="form" ref="formRef" label-position="top" :size="large" :rules="rules">
            <el-form-item label="" prop="title">
                <el-tag v-for="tag in dynamicTags" :key="tag" class="mx-1 mb-2" closable :disable-transitions="false"
                    @close="handleClose(tag)" type="info" round>
                    {{ tag }}
                </el-tag>
                <el-input v-if="inputVisible" ref="InputRef" v-model="inputValue" class="ml-1 w-20 mb-2" size="small"
                    @keyup.enter="handleInputConfirm" @blur="handleInputConfirm" />
                <el-button v-else class="button-new-tag ml-1 mb-2" size="small" @click="showInput" round>
                    + 新增标签
                </el-button>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="isTagPublishDialogShow = false">取消</el-button>
                <el-button type="primary" @click="addTagsSubmit">
                    提交
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
// import { ElMessage, ElMessageBox, ElInput } from 'element-plus'
import { ref, reactive } from 'vue'
import { addTags, getTagPageList, deleteTag } from '@/api/admin/tag'
import { showMessage } from '@/composables/util'
import moment from 'moment';
import { Search, RefreshRight } from '@element-plus/icons-vue'

const isTagPublishDialogShow = ref(false)

const searchTagName = ref('')
const pickDate = ref('')
const startDate = reactive({})
const endDate = reactive({})

const reset = () => {
    pickDate.value = ''
    startDate.value = null
    endDate.value = null
    searchTagName.value = ''
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

const form = reactive({
    tags: []
})

const inputValue = ref('')
const dynamicTags = ref([])
const inputVisible = ref(false)
// const InputRef = ref<InstanceType<ElInput>>()

const handleClose = (tag) => {
  dynamicTags.value.splice(dynamicTags.value.indexOf(tag), 1)
}

const showInput = () => {
  inputVisible.value = true
  nextTick(() => {
    // InputRef.value!.input!.focus()
  })
}

const handleInputConfirm = () => {
  if (inputValue.value) {
    dynamicTags.value.push(inputValue.value)
  }
  inputVisible.value = false
  inputValue.value = ''
}

const addTagsSubmit = () => {
    form.tags = dynamicTags.value
    addTags(form).then((e) => {
        console.log(e)
        if (e.success == false) {
            var message = e.message
            showMessage(message, 'warning', 'message')
            return
        }

        showMessage('添加成功', 'success', 'message')
        isTagPublishDialogShow.value = false
        dynamicTags.value = []
        getTableData()
    })
}

const tableLoading = ref(false)
const tableData = ref([])
// 当前页码
const current = ref(1)
const total = ref(0)
const size = ref(10)

// 获取分页数据
function getTableData() {
    console.log('获取分页数据')
    tableLoading.value = true
    getTagPageList({ current: current.value, size: size.value, startDate: startDate.value, endDate: endDate.value, tagName: searchTagName.value })
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

const deleteTagSubmit = (row) => {
    ElMessageBox.confirm(
        '是否确认要删除该标签?',
        '提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            deleteTag(row.id).then((e) => {
                if (e.success == true) {
                    showMessage('删除成功', 'success')
                    getTableData()
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

</script>

<style>
</style>