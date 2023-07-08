package com.quanxiaoha.weblog.admin.model.vo.dashboard;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryDashboardArticleStatisticsRspVO {
    private Long articleTotalCount;
    private Long categoryTotalCount;
    private Long tagTotalCount;
    private Long pvTotalCount;
}
