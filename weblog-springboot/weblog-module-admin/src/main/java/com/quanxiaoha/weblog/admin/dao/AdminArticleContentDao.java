package com.quanxiaoha.weblog.admin.dao;

import com.quanxiaoha.weblog.common.domain.dos.ArticleContentDO;

public interface AdminArticleContentDao {
    int insertArticleContent(ArticleContentDO articleContentDO);

    ArticleContentDO queryByArticleId(Long articleId);

    int deleteByArticleId(Long articleId);

    int updateByArticleId(ArticleContentDO articleContentDO);
}
