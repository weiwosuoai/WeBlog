<template>
    <Header></Header>

    <div class="container mx-auto mt-5">
        <el-row :gutter="20">
            <el-col :span="17" :offset="0">
                <!-- 轮播图 -->
                <!-- <el-card class="mb-5" shadow="none">
                    <el-carousel :interval="4000" type="card" :autoplay="true">
                        <el-carousel-item v-for="item in images" :key="item">
                            <a href="#"><img :src="item"></a>
                        </el-carousel-item>
                    </el-carousel>
                </el-card> -->

                <!-- 文章列表 -->
                <div class="el-card mb-3" v-for="(article, index) in articles" :key="index">
                    <el-row :gutter="20">
                        <el-col :span="10" :offset="0">
                            <a class="cursor-pointer" @click="goArticleDetail(article.id)">
                                <img class="article-img" :src="article.titleImage">
                            </a>
                        </el-col>
                        <el-col :span="14" :offset="0" class="pt-3 pb-3">
                            <div class="flex flex-col justify-between">
                                <h2 class="text-black font-bold text-2xl">
                                    <a class="cursor-pointer" @click="goArticleDetail(article.id)">{{ article.title }}</a>
                                </h2>
                                <div class="flex items-center text-gray-500 mt-3 text-sm">
                                    <el-icon class="mr-1 text-3">
                                        <Calendar />
                                    </el-icon>
                                    <span>{{ article.createTime }}</span>
                                </div>
                                <p class="pr-2 mt-3 text-gray-500 flex-grow text-sm">
                                    {{ article.description }}
                                </p>
                                <div class="mt-3 flex bottom-0 text-sm">
                                    <div class="flex items-center mr-5 text-gray-500">
                                        <el-tooltip class="box-item" effect="dark" content="标签" placement="bottom-start">
                                            <svg t="1682517355246" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                                xmlns="http://www.w3.org/2000/svg" p-id="26989" width="16" height="16">
                                                <path
                                                    d="M940.8 521.6l-448-448C486.4 67.2 480 64 470.4 64L124.8 64C92.8 64 64 92.8 64 124.8l0 345.6c0 9.6 3.2 16 9.6 22.4l448 448c12.8 12.8 28.8 19.2 44.8 19.2 16 0 32-6.4 44.8-19.2L940.8 608C966.4 585.6 966.4 544 940.8 521.6zM566.4 896 128 457.6 128 128l329.6 0L896 566.4 566.4 896z"
                                                    p-id="26990" fill="#707070"></path>
                                                <path
                                                    d="M288 192C233.6 192 192 233.6 192 288s41.6 96 96 96 96-41.6 96-96S342.4 192 288 192zM288 320c-19.2 0-32-12.8-32-32s12.8-32 32-32 32 12.8 32 32S307.2 320 288 320z"
                                                    p-id="26991" fill="#707070"></path>
                                            </svg>
                                        </el-tooltip>

                                        <a class="tag-item" @click="goTagArticleListPage(item.id, item.name)" v-for="(item, index) in article.tags"
                                            :key="index">
                                            <el-tag class="ml-2" type="info" size="small">
                                            {{ item.name }}
                                        </el-tag>
                                </a>

                                        
                                    </div>

                                    <div class="text-gray-500">
                                        <a @click="goCatagoryArticleListPage(article.category.id, article.category.name)"
                                            class="flex items-center category-item">
                                            <el-tooltip class="box-item" effect="dark" content="分类"
                                                placement="bottom-start">
                                                <el-icon class="mr-1">
                                        <FolderOpened />
                                    </el-icon>
                                            </el-tooltip>
                                            {{ article.category.name }}
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </div>

                <!-- 分页 -->
                <div class="flex justify-center">
                    <el-pagination class="mt-5" v-model:current-page="current" v-model:page-size="size" :small="small"
                        :disabled="disabled" background="true" layout="prev, pager, next" :total="total"
                        @current-change="getArticles" />
                </div>
            </el-col>
            <el-col :span="7" :offset="0">
                <el-affix :offset="70">
                    <UserInfoCard></UserInfoCard>

                    <!-- 文章分类 -->
                    <el-card shadow="none" class="mb-5">
                        <h2 class="font-bold mb-2">分类</h2>
                        <ul class="text-gray-500 ml-2">
                            <li class="flex items-center" v-for="(item, index) in categories" :key="index">
                                <a class="category-item" @click="goCatagoryArticleListPage(item.id, item.name)">
                                    <el-icon class="mr-1">
                                        <FolderOpened />
                                    </el-icon>
                                    {{ item.name }}
                                </a>
                            </li>
                        </ul>
                    </el-card>

                    <!-- 文章标签 -->
                    <el-card shadow="none">
                        <h2 class="font-bold mb-2">标签</h2>
                        <ul class="flex flex-wrap">
                            <li v-for="(item, index) in tags" :key="index">
                                <a class="tag-item" @click="goTagArticleListPage(item.id, item.name)">
                                    <el-tag class="mr-2 mb-2" type="info">{{ item.name }}</el-tag>
                                </a>
                            </li>
                        </ul>
                    </el-card>
                </el-affix>
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
import { reactive, ref } from 'vue'
import { getIndexArticles } from '@/api/frontend/index'
import { getCategories } from '@/api/frontend/category'
import { getTags } from '@/api/frontend/tag'

const router = useRouter()

const goArticleDetail = (articleId) => {
    console.log('跳转详情页' + articleId)
    router.push({ path: '/article/detail', query: { articleId: articleId } })
}

const articles = ref([])
// 当前页码
const current = ref(1)
const total = ref(0)
const size = ref(10)

// 获取分页数据
function getArticles() {
    console.log('获取分页数据')
    getIndexArticles({ current: current.value, size: size.value })
        .then((res) => {
            console.log(res)
            if (res.success == true) {
                articles.value = res.data
                current.value = res.current
                total.value = res.total
                size.value = res.size
            }
        })
}
getArticles()

// 获取分类
const categories = ref([])
getCategories().then((e) => {
    console.log('获取分类数据')
    console.log(e)
    categories.value = e.data
})


// 获取标签
const tags = ref([])
getTags().then((e) => {
    console.log('获取标签数据')
    console.log(e)
    tags.value = e.data
})


const goCatagoryArticleListPage = (id, name) => {
    router.push({ path: '/category/list', query: { id: id, name: name } })
}

const goTagArticleListPage = (id, name) => {
    console.log('跳转 id' + id)
    router.push({ path: '/tag/list', query: { id: id, name: name } })
}

</script>

<style>
.container {
    max-width: 1230px;
}

.article-img {
    height: 100%;
}

.two-line-clamp {
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
}

.el-menu--horizontal .el-menu-item:not(.is-disabled):focus,
.el-menu--horizontal .el-menu-item:not(.is-disabled):hover {
    outline: 0;
    color: var(--el-menu-text-color);
    ;
    background-color: #fff;
    border-bottom: 2px solid #409eff;
    ;
}

.category-item:hover {
    text-decoration: underline;
    cursor: pointer;
}

.tag-item:hover {
    cursor: pointer;
}

.el-tag:hover {
    background-color: var(--el-color-info-light-8);
}

.cursor-pointer {
    cursor: pointer;
}

</style>