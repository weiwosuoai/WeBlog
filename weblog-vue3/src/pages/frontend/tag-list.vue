<template>
    <Header></Header>
    <div class="container mx-auto mt-5">
        <el-row :gutter="20">
            <el-col :span="17" :offset="0">
                <el-card shadow="never">
                    <template #header>
                    <div>
                        <span class="flex items-center font-bold"><!-- card title -->
                            <svg t="1682517355246" class="icon mr-2" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="26989" 
                                            width="16" height="16"><path d="M940.8 521.6l-448-448C486.4 67.2 480 64 470.4 64L124.8 64C92.8 64 64 92.8 64 124.8l0 345.6c0 9.6 3.2 16 9.6 22.4l448 448c12.8 12.8 28.8 19.2 44.8 19.2 16 0 32-6.4 44.8-19.2L940.8 608C966.4 585.6 966.4 544 940.8 521.6zM566.4 896 128 457.6 128 128l329.6 0L896 566.4 566.4 896z" p-id="26990" fill="#707070"></path><path d="M288 192C233.6 192 192 233.6 192 288s41.6 96 96 96 96-41.6 96-96S342.4 192 288 192zM288 320c-19.2 0-32-12.8-32-32s12.8-32 32-32 32 12.8 32 32S307.2 320 288 320z" p-id="26991" fill="#707070"></path></svg>
                            <span style="margin-top: 3px;">标签</span></span>
                    </div>
                    </template>
                    <!-- card body -->
                    <ul class="flex flex-wrap">
                        <li v-for="(item, index) in tags" :key="index">
                            <a @click="goTagArticleListPage(item.id, item.name)">
                                <el-tag class="mr-5 mb-2 text-sm tag-item" type="info" size="large">{{ item.name }}</el-tag>
                            </a>
                        </li>
                    </ul>
                </el-card>
                
            </el-col>
            <el-col :span="7" :offset="0">
                <UserInfoCard></UserInfoCard>
            </el-col>
        </el-row>
        
    </div>
    <Footer></Footer>
</template>

<script setup>
import Header from '@/layouts/components/Header.vue'
import Footer from '@/layouts/components/Footer.vue'
import UserInfoCard from '@/components/UserInfoCard.vue'
import { useRouter } from 'vue-router'
import { getTags } from '@/api/frontend/tag'
import { ref } from 'vue'

const router = useRouter()

const goTagArticleListPage = (id, name) => {
    router.push({path: '/tag/list', query: {id: id, name: name}})
}

const tags = ref([])
getTags().then((e) => {
    if (e.success) {
        tags.value = e.data
    }
})
</script>