package com.quanxiaoha.weblog.admin.model.vo.category;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryCategoryPageListReqVO {
    private Long current = 1L;
    private Long size = 10L;
    private Date startDate;
    private Date endDate;
    private String categoryName;
}
