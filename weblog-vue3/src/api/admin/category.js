import axios from "@/axios"

export function addCategory(data) {
    return axios.post("/admin/category/add", data)
}

export function getCategoryPageList(data) {
    return axios.post("/admin/category/list", data)
}


export function deleteCategory(categoryId) {
    return axios.post("/admin/category/delete", {categoryId})
}

export function getCategorySelect() {
    return axios.post("/admin/category/select/list")
}

