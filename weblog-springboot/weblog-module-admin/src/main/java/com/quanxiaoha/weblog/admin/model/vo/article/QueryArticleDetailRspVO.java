package com.quanxiaoha.weblog.admin.model.vo.article;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryArticleDetailRspVO {
    private Long id;
    private String title;
    private String titleImage;
    private String content;
    private String description;
    private Long categoryId;
    private List<Long> tagIds;
}
