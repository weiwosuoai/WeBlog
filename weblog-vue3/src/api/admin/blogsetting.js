import axios from "@/axios"

export function getBlogSettingDetail() {
    return axios.post("/admin/blog/setting/detail")
}

export function updateBlogSetting(data) {
    return axios.post("/admin/blog/setting/update", data)
}



