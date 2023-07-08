package com.quanxiaoha.weblog.admin.service;


import com.quanxiaoha.weblog.common.Response;

public interface AdminDashboardService {

    Response queryDashboardArticleStatisticsInfo();

    Response queryDashboardPublishArticleStatisticsInfo();

    Response queryDashboardPVStatisticsInfo();
}
