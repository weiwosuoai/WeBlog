<template>
    <Header></Header>

    <div class="container mx-auto max-w-screen-xl mt-5">
        <div class="grid grid-cols-4">
            <!-- 左边栏 -->
            <div class="col-span-4 px-3 md:col-span-3 sm:col-span-4">

                <div class="mb-3 w-full font-medium p-5 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
                        <h2 class="mb-2 font-bold text-gray-900 uppercase dark:text-white">标签</h2>
                        <div @click="goTagArticleListPage(item.id, item.name)" v-for="(item, index) in tags" :key="index"
                            class="inline-block rounded-full bg-green-100 text-green-800 text-sm font-medium mr-2 mb-2 px-2.5 py-0.5 rounded dark:bg-green-900 dark:text-green-300 hover:bg-green-200 hover:text-green-900">
                            {{ item.name }}
                        </div>
                    </div>
            </div>
            <!-- 右边栏 -->
            <div class="col-span-4 px-3 md:col-span-1 sm:col-span-4">
                <UserInfoCard></UserInfoCard>
            </div>
        </div>
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