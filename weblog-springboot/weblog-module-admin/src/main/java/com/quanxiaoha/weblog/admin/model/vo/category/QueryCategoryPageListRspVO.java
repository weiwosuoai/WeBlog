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
public class QueryCategoryPageListRspVO {
    private Long id;
    private String name;
    private Date createTime;
}
