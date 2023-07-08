import axios from "@/axios"

export function addTags(data) {
    return axios.post("/admin/tag/add", data)
}

export function getTagPageList(data) {
    return axios.post("/admin/tag/list", data)
}

export function deleteTag(tagId) {
    return axios.post("/admin/tag/delete", {tagId})
}

export function selectTags(key) {
    return axios.post("/admin/tag/search", {key})
}

export function getTagSelect() {
    return axios.post("/admin/tag/select/list")
}


