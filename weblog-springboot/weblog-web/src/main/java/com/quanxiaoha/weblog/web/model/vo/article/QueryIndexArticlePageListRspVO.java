package com.quanxiaoha.weblog.web.model.vo.article;


import com.quanxiaoha.weblog.web.model.vo.category.QueryCategoryListRspVO;
import com.quanxiaoha.weblog.web.model.vo.tag.QueryTagListRspVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryIndexArticlePageListRspVO {
    private Long id;
    private String titleImage;
    private String title;
    private String createTime;
    private String description;
    private QueryCategoryListRspVO category;
    private List<QueryTagListRspVO> tags;
}
