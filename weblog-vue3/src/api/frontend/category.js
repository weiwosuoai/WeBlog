import axios from "@/axios"

export function getCategories() {
    return axios.post("/category/list")
}

export function getCategoryArticles(data) {
    return axios.post("/category/article/list", data)
}


