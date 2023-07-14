<template>
    <el-card shadow="never" :body-style="{ padding: '20px' }" class="mb-5 border-1">
        <!-- card body -->
        <el-text class="mx-1 mr-3">分类名称</el-text>
        <el-input v-model="searchCategoryName" placeholder="请输入（模糊查询）" class="w-50 mr-5" />

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
            <el-button type="primary" @click="isAddCatagoryDialogShow = true">
                <el-icon class="mr-1">
                    <Plus />
                </el-icon>
                新增</el-button>
        </div>

        <el-table :data="tableData" stripe style="width: 100%" class="mt-4" v-loading="tableLoading">
            <!-- <el-table-column prop="id" label="ID" width="180" /> -->
            <el-table-column prop="name" label="分类名称" width="180" >
                
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="180" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="danger" size="small" @click="deleteCategorySubmit(scope.row)">
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

    <!-- 新增分类 -->
    <el-dialog v-model="isAddCatagoryDialogShow" title="新增分类" width="30%" :show-close="false" draggable>
        <el-form :model="form" ref="formRef" label-position="top" :size="large" :rules="rules">
            <el-form-item label="分类名称" prop="name">
                <el-input v-model="form.name" autocomplete="off" size="large" maxlength="10" show-word-limit clearable />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="isAddCatagoryDialogShow = false">取消</el-button>
                <el-button type="primary" @click="addCategorySubmit">
                    提交
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
// import { ElMessage, ElMessageBox } from 'element-plus'
import { ref, reactive } from 'vue'
import { addCategory, getCategoryPageList, deleteCategory } from '@/api/admin/category'
import { showMessage } from '@/composables/util'
import moment from 'moment';
import { Search, RefreshRight } from '@element-plus/icons-vue'

const isAddCatagoryDialogShow = ref(false)

const searchCategoryName = ref('')
const pickDate = ref('')
const startDate = reactive({})
const endDate = reactive({})

const reset = () => {
    pickDate.value = ''
    startDate.value = null
    endDate.value = null
    searchCategoryName.value = ''
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
    name: ''
})

const formRef = ref(null)
const rules = {
    name: [
        { required: true, message: '请输入分类名称', trigger: 'blur' },
        { min: 1, max: 10, message: '分类名称字数要求大于1个字符，小于20个字符', trigger: 'blur' },
    ]
}

const addCategorySubmit = () => {
    addCategory(form).then((e) => {
        console.log(e)
        if (e.success == false) {
            var message = e.message
            showMessage(message, 'warning', 'message')
            return
        }

        showMessage('添加成功', 'success', 'message')
        isAddCatagoryDialogShow.value = false
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
    getCategoryPageList({ current: current.value, size: size.value, startDate: startDate.value, endDate: endDate.value, categoryName: searchCategoryName.value })
        .then((res) => {
            if (res.success == true) {
                tableData.value = res.data
                current.value = res.current
                total.value = res.total
                size.value = res.size
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

const deleteCategorySubmit = (row) => {
    ElMessageBox.confirm(
        '是否确认要删除该分类?',
        '提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            deleteCategory(row.id).then((e) => {
                if (e.success == true) {
                    showMessage('删除成功', 'success')
                    getTableData()
                } else {
                    let message = e.message
                    showMessage(message, 'warning')
                }
            })

        })
}


</script>

<style>
</style>