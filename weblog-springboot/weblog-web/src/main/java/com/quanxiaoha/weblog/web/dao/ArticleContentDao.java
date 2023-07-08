package com.quanxiaoha.weblog.web.dao;

import com.quanxiaoha.weblog.common.domain.dos.ArticleContentDO;

public interface ArticleContentDao {
    ArticleContentDO selectArticleContentByArticleId(Long articleId);
}
