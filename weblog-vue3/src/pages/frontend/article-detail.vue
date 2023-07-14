<template>
    <Header></Header>

    <!-- 文章详情 -->
    <div class="container mx-auto max-w-screen-xl mt-5 mb-3">
        <div class="grid grid-cols-4">
            <!-- 左边栏 -->
            <div class="col-span-4 px-3 md:col-span-3 sm:col-span-4">
                <div class="bg-white border border-gray-200 p-5 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                    <!-- 面包屑 -->
                    <div class="pb-5">
                        <span>
                            <el-breadcrumb separator="/" class="flex items-center">
                                <svg class="w-3 h-3 mr-2 mb-1px text-gray-600 dark:text-white" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                                    <path
                                        d="m19.707 9.293-2-2-7-7a1 1 0 0 0-1.414 0l-7 7-2 2a1 1 0 0 0 1.414 1.414L2 10.414V18a2 2 0 0 0 2 2h3a1 1 0 0 0 1-1v-4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v4a1 1 0 0 0 1 1h3a2 2 0 0 0 2-2v-7.586l.293.293a1 1 0 0 0 1.414-1.414Z" />
                                </svg>
                                <el-breadcrumb-item>
                                    <a href="#" @click="router.push('/')">首页</a>
                                </el-breadcrumb-item>
                                <el-breadcrumb-item>正文</el-breadcrumb-item>
                            </el-breadcrumb>
                        </span>
                    </div>

                    <!-- 文章主体 -->
                    <article>
                        <h1 class="title mt-2">{{ article.title }}</h1>
                        <div class="text-gray-500 text-sm flex items-center article-mata">
                            <svg t="1682495071994" class="icon mr-2" viewBox="0 0 1024 1024" version="1.1"
                                xmlns="http://www.w3.org/2000/svg" p-id="14886" width="16" height="16">
                                <path
                                    d="M736 544H512c-17.6 0-32-14.4-32-32V224c0-17.6 14.4-32 32-32s32 14.4 32 32v256h192c17.6 0 32 14.4 32 32s-14.4 32-32 32z m161.6-153.6c-17.6 0-32-14.4-32-32V185.6c0-17.6 14.4-32 32-32s32 14.4 32 32v172.8c0 17.6-14.4 32-32 32zM512 960C265.6 960 64 758.4 64 512S265.6 64 512 64c184 0 347.2 110.4 416 281.6 6.4 16-1.6 35.2-17.6 41.6-16 6.4-35.2-1.6-41.6-17.6C809.6 222.4 670.4 128 512 128c-211.2 0-384 172.8-384 384s172.8 384 384 384c158.4 0 297.6-94.4 356.8-241.6 6.4-16 25.6-24 41.6-17.6 16 6.4 24 25.6 17.6 41.6C859.2 849.6 696 960 512 960z"
                                    fill="#8a8a8a" p-id="14887"></path>
                            </svg>
                            2023-04-26 12:00:00
                        </div>

                        <div class="article-content" v-html="article.content" v-highlightjs>
                        </div>
                    </article>
                    <!-- 上下篇 -->
                    <div class="article-footer flex">
                        <div class="cursor-pointer">
                            <a v-if="article.preArticleId" @click="goArticleDetail(article.preArticleId)">
                                <span class="desc">
                                    <svg t="1686900913558" class="icon" style="display: inline;" viewBox="0 0 1024 1024"
                                        version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2276" width="20" height="20">
                                        <path
                                            d="M682.666667 810.666667a42.666667 42.666667 0 0 1-30.08-12.586667l-256-256a42.666667 42.666667 0 0 1 0-60.16l256-256a42.666667 42.666667 0 1 1 60.16 60.16L487.04 512l225.706667 225.92A42.666667 42.666667 0 0 1 682.666667 810.666667z"
                                            fill="#bfbfbf" p-id="2277"></path>
                                    </svg>
                                    上一篇</span>
                                <span>{{ article.preArticleTitle }}</span>
                            </a>
                        </div>
                        <div class="cursor-pointer">
                            <a v-if="article.nextArticleId" @click="goArticleDetail(article.nextArticleId)">
                                <span class="desc">
                                    下一篇
                                    <svg t="1686901823331" class="icon" style="display: inline;" viewBox="0 0 1024 1024"
                                        version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3410" width="20" height="20">
                                        <path
                                            d="M384 810.666667a42.666667 42.666667 0 0 1-30.08-72.746667L579.626667 512 353.92 286.08a42.666667 42.666667 0 0 1 60.16-60.16l256 256a42.666667 42.666667 0 0 1 0 60.16l-256 256A42.666667 42.666667 0 0 1 384 810.666667z"
                                            fill="#bfbfbf" p-id="3411"></path>
                                    </svg>
                                </span>
                                <span>{{ article.nextArticleTitle }}</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 右边栏 -->
            <div class="col-span-4 px-3 md:col-span-1 sm:col-span-4">
                <div class="sticky top-21">
                    <UserInfoCard></UserInfoCard>

                    <!-- 文章分类 -->
                    <div
                        class="mb-3 w-full font-medium p-5 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                        <h2 class="mb-2 font-bold text-gray-900 uppercase dark:text-white">分类</h2>
                        <div
                            class="text-sm font-medium text-gray-900 bg-white rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-white">
                            <a @click="goCatagoryArticleListPage(item.id, item.name)" v-for="(item, index) in categories"
                                :key="index"
                                class="flex items-end block w-full px-4 py-2 rounded-lg cursor-pointer hover:bg-gray-100 hover:text-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-700 focus:text-blue-700 dark:border-gray-600 dark:hover:bg-gray-600 dark:hover:text-white dark:focus:ring-gray-500 dark:focus:text-white">
                                <svg class="w-4 h-4 mr-2 mb-2px text-gray-800 inline dark:text-white" aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 21 18">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="0.9"
                                        d="M2.539 17h12.476l4-9H5m-2.461 9a1 1 0 0 1-.914-1.406L5 8m-2.461 9H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.443a1 1 0 0 1 .8.4l2.7 3.6H16a1 1 0 0 1 1 1v2H5" />
                                </svg>
                                {{ item.name }}
                            </a>
                        </div>
                    </div>

                    <!-- 文章标签 -->
                    <div
                        class="mb-3 w-full font-medium p-5 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                        <h2 class="mb-2 font-bold text-gray-900 uppercase dark:text-white">标签</h2>
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
import { useRoute, useRouter } from 'vue-router';
import { getArticleDetail } from '@/api/frontend/article';
import { ref, reactive } from 'vue'
import { getCategories } from '@/api/frontend/category'
import { getTags } from '@/api/frontend/tag'

const router = useRouter()
const route = useRoute()
const article = reactive({
    title: '',
    content: '',
    preArticleId: null,
    preArticleTitle: '',
    nextArticleId: null,
    nextArticleTitle: ''
})

function queryArticleDetail(articleId) {
    console.log('调用获取详情接口...' + route.query.articleId)
    getArticleDetail(articleId).then((e) => {
        article.title = e.data.title
        article.content = e.data.content
        if (e.data.preArticle) {
            console.log('上一篇...')
            console.log(e.data.preArticle)
            article.preArticleId = e.data.preArticle.id
            article.preArticleTitle = e.data.preArticle.title
        } else {
            article.preArticleId = null
        }

        if (e.data.nextArticle) {
            article.nextArticleId = e.data.nextArticle.id
            article.nextArticleTitle = e.data.nextArticle.title
        } else {
            article.nextArticleId = null
        }
    })
}
queryArticleDetail(route.query.articleId);

const goArticleDetail = (articleId) => {
    console.log('跳转详情页' + articleId)
    router.push({ path: '/article/detail', query: { articleId: articleId } })
    queryArticleDetail(articleId)
}


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
    router.push({ path: '/tag/list', query: { id: id, name: name } })
}

</script>

<style scoped>
.title {
    padding-bottom: 20px;
    margin-bottom: 0;
    line-height: 1.3;
    word-wrap: break-word;
    font-size: 32px;
    font-weight: 700;
    color: #292525;
}

.article-mata {
    margin-bottom: 20px;
}

:deep(pre) {
    background: #21252b;
    color: #f8f8f2;
    border-radius: 5px;
    padding: 32px 0 0;
    font-size: 16px;
}

:deep(pre:before) {
    background: #fc625d;
    border-radius: 50%;
    box-shadow: 20px 0 #fdbc40, 40px 0 #35cd4b;
    content: ' ';
    height: 10px;
    margin-top: -20px;
    position: absolute;
    width: 10px;
}

:deep(.article-content p) {
    letter-spacing: 0.3px;
    margin: 0 0 20px 0;
    line-height: 30px;
    color: #4c4e4d;
    font-weight: 400;
    word-break: normal;
    word-wrap: break-word;
    font-family: -apple-system, BlinkMacSystemFont, PingFang SC, Hiragino Sans GB, Microsoft Yahei, Arial, sans-serif;
}

:deep(.image-caption) {
    min-width: 20%;
    max-width: 80%;
    min-height: 43px;
    display: block;
    padding: 10px;
    margin: 0 auto;
    /* border-bottom: 1px solid #eee; */
    font-size: 13px;
    color: #999;
    text-align: center;
}

:deep(pre) {
    padding-left: 15px;
    padding-right: 15px;
    padding-bottom: 10px;
}

:deep(code) {
    display: inline;
    font-family: Operator Mono, Consolas, Monaco, Menlo, monospace;
    border-radius: 2px;
    padding: 5px;
}

:deep(article ul) {
    padding-left: 40px;
}

:deep(article ul li) {
    list-style-type: disc;
    padding-top: 5px;
    padding-bottom: 5px;
    font-size: 16px;
}

:deep(pre code) {
    display: block;
    font-size: 95% !important;
    background-color: rgba(27, 31, 35, 0.05);
    font-family: Operator Mono, Consolas, Monaco, Menlo, monospace;
}

:deep(blockquote) {
    /* margin: 20px 0; */
    border-left: 2.3px solid rgb(52, 152, 219);
    quotes: none;
    background: rgb(236, 240, 241);
    color: #777;
    font-size: 16px;
    /* padding: 10px 15px 10px 15px; */
    margin: 2em 0;
    padding: 24px 24px;
    position: relative;
}

:deep(blockquote p:last-child) {
    margin-bottom: 0;
}

:deep(table tr) {
    background-color: #fff;
    border-top: 1px solid #c6cbd1;
}

:deep(table) {
    border-collapse: collapse;
    margin-bottom: 1rem;
}

:deep(table th) {
    padding: 6px 13px;
    border: 1px solid #dfe2e5;
}

:deep(table td) {
    padding: 6px 13px;
    border: 1px solid #dfe2e5;
}

:deep(.article-content a) {
    color: #167bc2;
}

:deep(.article-content h2) {
    /* margin: 1em auto; */
    font-size: 22px;
    line-height: 1.5;
    font-weight: bold;
    font-synthesis: style;
    /* border-bottom: 1px solid rgba(0,0,0,.1); */
    padding-bottom: 16px;
    /* border-left: 3px solid #167bc2; */
    padding-bottom: 0;
    font-size: 24px;
    margin-top: 40px;
    margin-bottom: 26px;
    line-height: 140%;
    border-bottom: 1px solid #e5e5e5;
    padding-bottom: 15px;
}

:deep(.article-content svg) {
    display: inline;
}

:deep(.article-content img) {
    position: relative;
    max-width: 100%;
    overflow: hidden;
    display: block;
    margin: 0 auto;
}

:deep(strong) {
    color: rgb(52, 152, 219);
}

:deep(table tr:nth-child(2n)) {
    background-color: #f6f8fa;
}

.el-breadcrumb__inner a {
    font-weight: 400;
    color: #606266;
}

.el-breadcrumb__inner a:hover {
    font-weight: 400;
    color: #606266;
    text-decoration: underline;
}

.article-footer {
    border-top: 1px solid #e4e7ed;
    background-color: var(--el-fill-color-blank);
    justify-content: space-between;
    padding-top: 1rem;
}

.desc {
    display: block;
    font-size: 12px;
    color: rgba(60, 60, 60, .7);
    ;
}

.cursor-pointer {
    cursor: pointer;
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
</style>