<template>
    <Header></Header>

    <!-- 文章列表 -->
    <div class="container mx-auto max-w-screen-xl mt-5">
        <div class="grid grid-cols-4">
            <!-- 左边栏 -->
            <div class="col-span-4 px-3 md:col-span-3 sm:col-span-4">
                <!-- 分类标题 -->
                <div class="flex items-center mb-5 text-gray-600 font-bold category-container text-2xl">
                    <svg class="w-6 h-6 mr-2 text-gray-600 inline dark:text-white" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 21 18">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="0.9"
                                        d="M2.539 17h12.476l4-9H5m-2.461 9a1 1 0 0 1-.914-1.406L5 8m-2.461 9H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.443a1 1 0 0 1 .8.4l2.7 3.6H16a1 1 0 0 1 1 1v2H5" />
                                </svg>
                    {{ categoryName }}
                </div>
                <!-- 文章列表 -->
                <div v-if="articles && articles.length > 0" class="grid grid-cols-1 sm:grid-cols-1 md:grid-cols-2 gap-4">

                    <div v-for="(article, index) in articles" :key="index"
                        class="bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                        <a @click="goArticleDetail(article.id)" class="cursor-pointer">
                            <img class="rounded-t-lg h-50 w-full" :src="article.titleImage" />
                        </a>
                        <div class="p-5">
                            <a @click="goArticleDetail(article.id)" class="cursor-pointer">
                                <h2 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">{{
                                    article.title }}</h2>
                            </a>
                            <p class="mb-3 font-normal text-gray-500 dark:text-gray-400">{{ article.description }}</p>
                            <p>
                            <div @click="goTagArticleListPage(item.id, item.name)" v-for="(item, index) in article.tags"
                                :key="index"
                                class="inline-block bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:hover:bg-green-800 dark:hover:text-green-300 dark:bg-green-900 dark:text-green-300">
                                {{ item.name }}
                            </div>
                            </p>
                        </div>
                    </div>
                </div>
                

                <!-- <div class="el-card mb-3" v-if="articles && articles.length > 0" v-for="(article, index) in articles"
                    :key="index">
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

                                        <a class="tag-item" @click="goTagArticleListPage(item.id, item.name)"
                                            v-for="(item, index) in article.tags" :key="index">
                                            <el-tag class="ml-2" type="info" size="small">
                                                {{ item.name }}
                                            </el-tag>
                                        </a>


                                    </div>

                                </div>
                            </div>
                        </el-col>
                    </el-row>

                </div> -->
                <div v-else>
                    <div class="flex flex-col items-center mb-5">
                        <svg t="1687255143784" class="icon" viewBox="0 0 1576 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="1447" width="200" height="200">
                            <path
                                d="M1260.533 834.866h-134.81l9.387-15.697c6.31-11.08 9.388-23.7 9.388-37.088V168.82c0-19.698-7.849-37.858-21.237-52.016-13.389-13.388-32.318-21.237-52.016-21.237H567.093c-19.698 0-37.857 7.849-52.015 21.237-14.158 14.158-21.237 32.318-21.237 52.016v32.317h-73.253c-19.698 0-37.858 7.849-52.016 21.238-14.158 14.158-22.006 32.317-22.006 52.015v612.338c0 12.62 3.078 25.238 9.387 37.088l9.388 15.697H251.92c-5.54 0-10.31 4.77-10.31 10.31 0 2.31 0.77 5.541 3.232 7.08 1.539 1.539 4.77 3.078 7.079 3.078h533.545l54.324 54.324c5.54 5.54 14.158 9.387 22.006 9.387 7.849 0 16.62-3.078 22.007-9.387 10.31-10.311 11.85-26.008 4.77-38.627l-9.387-16.62h129.27c5.54 0 10.31-4.771 10.31-10.311s-4.77-10.311-10.31-10.311h-30.01l9.388-15.697c6.31-11.08 9.388-23.7 9.388-37.088v-32.318h263.156c5.54 0 10.31-4.77 10.31-10.31 0.154-4.156-4.616-8.157-10.156-8.157z m-283.624 52.016c0 29.086-23.7 52.785-52.785 52.785h-70.945l-73.252-74.022c-7.849-7.849-19.699-11.08-30.01-7.849l-6.309 1.54-30.778-30.78 5.54-7.078c42.628-57.556 33.856-138.657-19.699-186.056-53.554-47.245-135.579-44.167-186.055 7.079-50.477 50.476-52.786 132.347-6.31 186.825 46.475 53.555 127.73 63.096 184.363 20.468l7.08-5.54 30.778 30.778-1.54 6.31c-3.077 11.08 0 22.93 7.85 30.778l29.085 29.086H419.665c-29.086 0-52.786-23.7-52.786-52.785V273.775c0-28.317 22.93-52.016 51.247-52.016h507.537c28.316 0 51.246 23.7 51.246 52.016v613.107z m-286.086-65.404c-22.93 22.93-52.016 33.856-82.025 33.856s-59.094-11.08-82.024-33.856c-21.853-21.7-34.01-51.247-33.857-82.025 0-30.779 11.85-60.634 33.857-82.025 22.006-22.006 51.246-33.856 82.024-33.856s59.864 11.85 82.025 33.856c22.007 22.007 33.856 51.246 33.856 82.025s-11.85 60.018-33.856 82.025z m433.36-40.166c0.001 29.085-23.698 52.785-52.784 52.785h-74.022V274.544c0-19.698-7.849-37.857-21.238-52.016-13.388-13.388-32.317-21.237-52.015-21.237H514.308v-31.548c0-29.085 23.7-52.785 52.785-52.785h504.46c29.085 0 52.785 23.7 52.785 52.785v611.569zM167.436 940.436H41.397c-5.54 0-10.31 4.771-10.31 10.311 0 3.078 0.769 5.54 3.077 7.08 1.539 1.538 4.77 3.077 7.08 3.077H167.28c5.54 0 10.31-4.77 10.31-10.31s-3.846-10.158-10.156-10.158z m0 0"
                                fill="#bfbfbf" p-id="1448"></path>
                            <path
                                d="M482.76 327.33h230.993c5.54 0 10.31-4.772 10.31-10.312s-4.77-10.31-10.31-10.31H482.76c-5.54 0-10.31 4.77-10.31 10.31 0 2.309 0.77 5.54 3.078 7.08 1.692 2.462 4.77 3.231 7.232 3.231z m336.563 85.102H482.76c-5.54 0-10.31 4.77-10.31 10.31 0 3.078 0.77 5.54 3.078 7.08 1.538 1.538 4.77 3.077 7.079 3.077h336.562c5.54 0 10.311-4.77 10.311-10.31 0-5.387-4.77-10.157-10.157-10.157z m-189.288 105.57H482.607c-5.54 0-10.311 4.77-10.311 10.31 0 3.078 0.77 5.54 3.078 7.08 1.539 1.539 4.77 3.077 7.079 3.077h147.429c5.54 0 10.31-4.77 10.31-10.31s-4.616-10.157-10.157-10.157zM157.278 707.905h21.237c5.54 0 10.311 4.77 10.311 10.31s-4.77 10.312-10.31 10.312h-21.238v21.237c0 5.54-4.77 10.31-10.31 10.31-3.079 0-5.54-0.769-7.08-3.077-2.308-1.54-3.078-4.771-3.078-7.08V728.68h-21.39c-5.54 0-10.311-4.77-10.311-10.31s4.77-10.311 10.31-10.311h21.237v-21.237c0-5.54 4.771-10.311 10.311-10.311s10.311 4.77 10.311 10.31v21.084z m1387.032-85.102v-21.238c0-3.231-0.77-5.54-3.078-7.079-2.308-1.539-4.77-3.078-7.079-3.078-5.54 0-10.31 4.771-10.31 10.311v21.237h-21.238c-3.077 0-5.54 0.77-7.079 3.232-1.539 2.309-3.231 4.77-3.231 7.08 0 5.54 4.77 10.31 10.31 10.31h21.238v21.237c0 5.54 4.77 10.31 10.31 10.31s10.311-4.77 10.311-10.31V643.27h21.237c5.54 0 10.311-4.77 10.311-10.31s-4.77-10.311-10.31-10.311h-21.392zM267.62 47.553h31.548c8.618 0 15.697 7.079 15.697 15.697s-7.08 15.697-15.697 15.697h-31.548v31.548c0 8.618-7.08 15.697-15.697 15.697-4.001 0-7.849-1.54-11.08-4.77-3.078-2.31-4.771-6.31-4.771-11.081V79.1h-31.548c-4.001 0-7.849-1.54-11.08-4.771-3.078-2.308-4.77-6.31-4.77-11.08 0-8.618 7.078-15.697 15.696-15.697h31.548V16.005c0-8.618 7.08-15.697 15.697-15.697s15.697 7.079 15.697 15.697v31.548zM62.634 274.544c-22.93 0-43.397 11.85-54.324 31.548-11.08 19.698-11.08 44.167 0 63.096 11.08 19.698 32.318 31.548 54.324 31.548 34.626 0 63.096-28.316 63.096-63.096s-28.47-63.096-63.096-63.096z m27.547 79.562c-5.54 9.388-15.697 15.697-27.547 15.697-17.39 0-31.548-14.158-31.548-31.548s14.158-31.547 31.548-31.547c11.08 0 21.237 6.31 27.547 15.697 5.54 9.695 5.54 21.39 0 31.701z m1275.306-205.754c-22.93 0-43.397 11.85-54.324 31.548-11.08 19.698-11.08 44.167 0 63.096 11.08 19.698 32.318 31.548 54.324 31.548 34.626 0 63.096-28.316 63.096-63.096-0.154-34.626-27.7-63.096-63.096-63.096z m27.547 78.793c-5.54 9.388-15.697 15.697-27.547 15.697-17.39 0-31.548-14.158-31.548-31.548s14.158-31.548 31.548-31.548c11.08 0 22.007 6.31 27.547 15.697 5.54 9.696 5.54 22.315 0 31.702z m0 0"
                                fill="#bfbfbf" p-id="1449"></path>
                        </svg>
                        <p class="text-gray-600 mt-5 text-lg font-blod">此分类下还未发布博客哟~</p>
                    </div>
                </div>

                <!-- 分页 -->
                <nav aria-label="Page navigation example" v-if="total > 0">
                    <ul class="flex items-center justify-center mt-10 mb-10 -space-x-px h-10 text-base">
                        <li>
                            <a v-if="current > 1" @click="getArticles(current - 1)"
                                class="flex items-center justify-center px-4 h-10 ml-0 leading-tight text-gray-500 bg-white border border-gray-300 rounded-l-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                <span class="sr-only">Previous</span>
                                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                    viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M5 1 1 5l4 4" />
                                </svg>
                            </a>
                            <a v-else @click="getArticles(current)"
                                class="cursor-not-allowed flex items-center justify-center px-4 h-10 ml-0 leading-tight text-gray-500 bg-white border border-gray-300 rounded-l-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                <span class="sr-only">Previous</span>
                                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                    viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="M5 1 1 5l4 4" />
                                </svg>
                            </a>
                        </li>
                        <li v-for="page in pages" :key="page">
                            <a @click="getArticles(page)"
                                class="flex items-center border-gray-300 justify-center px-4 h-10 leading-tight bg-white border dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
                                :class="[page == current ? 'text-blue-600 bg-blue-50 hover:bg-blue-100 hover:text-blue-700' : 'text-gray-500  hover:bg-gray-100 hover:text-gray-700']"
                                >
                                
                                {{ page }}
                            </a>
                        </li>
                        <li>
                            <a v-if="current < pages" @click="getArticles(current + 1)"
                                class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-r-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                <span class="sr-only">Next</span>
                                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                    viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="m1 9 4-4-4-4" />
                                </svg>
                            </a>
                            <a v-else="current == pages" @click="getArticles(current)"
                                class="cursor-not-allowed flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-r-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                                <span class="sr-only">Next</span>
                                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                    viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" d="m1 9 4-4-4-4" />
                                </svg>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <!-- 右边栏 -->
            <div class="col-span-4 px-3 md:col-span-1 sm:col-span-4">
                <div class="sticky top-21">
                    <UserInfoCard></UserInfoCard>

                    <!-- 文章标签 -->
                    <div
                        class="mb-3 w-full font-medium p-5 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                        <h4 class="mb-2 font-bold text-gray-900 uppercase dark:text-white">标签</h4>
                        <div @click="goTagArticleListPage(item.id, item.name)" v-for="(item, index) in tags" :key="index"
                            class="inline-block bg-green-100 text-green-800 text-xs font-medium mr-2 mb-1 px-2.5 py-0.5 rounded hover:bg-green-200 hover:text-green-900 dark:hover:bg-green-800 dark:hover:text-green-300 dark:bg-green-900 dark:text-green-300">
                            {{ item.name }}
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <Footer></Footer>
</template>

<script setup>
import Header from '@/layouts/components/Header.vue'
import Footer from '@/layouts/components/Footer.vue'
import UserInfoCard from '@/components/UserInfoCard.vue'
import { useRouter, useRoute } from 'vue-router'
import { ref } from 'vue'
import { getCategoryArticles } from '@/api/frontend/category'
import { getTags } from '@/api/frontend/tag'


const router = useRouter()
const route = useRoute()

const categoryName = ref(route.query.name)

const goArticleDetail = (articleId) => {
    console.log('跳转详情页' + articleId)
    router.push({ path: '/article/detail', query: { articleId: articleId } })
}

const articles = ref([])
// 当前页码
const current = ref(1)
const total = ref(0)
const size = ref(10)
const pages = ref(0)

// 获取分页数据
function getArticles(currentNo) {
    console.log('获取分页数据')
    let categoryId = route.query.id
    getCategoryArticles({ current: currentNo, size: size.value, categoryId: categoryId })
        .then((res) => {
            console.log(res)
            if (res.success == true) {
                articles.value = res.data
                current.value = res.current
                total.value = res.total
                size.value = res.size
                pages.value = res.pages
            }
        })
}
getArticles(current.value)

const goTagArticleListPage = (id, name) => {
    router.push({ path: '/tag/list', query: { id: id, name: name } })
}

// 获取标签
const tags = ref([])
getTags().then((e) => {
    console.log('获取标签数据')
    console.log(e)
    tags.value = e.data
})

</script>

<style scoped>
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