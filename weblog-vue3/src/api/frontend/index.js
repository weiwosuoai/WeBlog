import axios from "@/axios"

export function getIndexArticles(data) {
    return axios.post("/index/article/list", data)
}


