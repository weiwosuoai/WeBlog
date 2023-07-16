import axios from "@/axios"


export function getArchives(data) {
    return axios.post("/archive/list", data)
}
