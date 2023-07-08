package com.quanxiaoha.weblog.admin.dao;

import com.quanxiaoha.weblog.common.domain.dos.ArticleCategoryRelDO;

public interface AdminArticleCategoryRelDao {
    int insert(ArticleCategoryRelDO articleCategoryRelDO);

    ArticleCategoryRelDO selectByArticleId(Long articleId);

    int deleteByArticleId(Long articleId);
}
