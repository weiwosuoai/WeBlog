package com.quanxiaoha.weblog.web.model.vo.article;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryCategoryArticlePageListReqVO {
    private Long current = 1L;
    private Long size = 10L;
    // 分类
    @NotNull(message = "分类 ID 不能为空")
    private Long categoryId;
}
