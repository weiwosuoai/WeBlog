import axios from "@/axios"

export function getBlogSettingDetail() {
    return axios.post("/blog/setting/detail")
}




