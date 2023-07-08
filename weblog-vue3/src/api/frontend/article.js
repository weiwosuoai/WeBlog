import axios from "@/axios"

export function getArticleDetail(articleId) {
    return axios.post("/article/detail", {articleId})
}


