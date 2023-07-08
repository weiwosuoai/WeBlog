import axios from "@/axios"

export function getDashboardArticleStatisticsInfo() {
    return axios.post("/admin/dashboard/article/statistics")
}

export function getDashboardPublishArticleStatisticsInfo() {
    return axios.post("/admin/dashboard/publishArticle/statistics")
}

export function getDashboardPVStatisticsInfo() {
    return axios.post("/admin/dashboard/pv/statistics")
}


