import axios from "@/axios"

export function uploadFile(file) {
    return axios.post("/admin/file/upload", file)
}

