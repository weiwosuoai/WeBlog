import axios from "@/axios"

export function getTags() {
    return axios.post("/tag/list")
}

export function getTagArticles(data) {
    return axios.post("/tag/article/list", data)
}
