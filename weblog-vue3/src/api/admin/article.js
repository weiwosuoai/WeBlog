import axios from "@/axios"

export function getArticleDetail(articleId) {
    return axios.post("/admin/article/detail", {articleId})
}

export function publishArticle(data) {
    return axios.post("/admin/article/publish", data)
}

export function getArticlePageList(data) {
    return axios.post("/admin/article/list", data)
}

export function deleteArticle(articleId) {
    return axios.post("/admin/article/delete", {articleId})
}

export function updateArticle(data) {
    return axios.post("/admin/article/update", data)
}
