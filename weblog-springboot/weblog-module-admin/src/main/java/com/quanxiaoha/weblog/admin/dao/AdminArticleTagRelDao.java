package com.quanxiaoha.weblog.admin.dao;

import com.quanxiaoha.weblog.common.domain.dos.ArticleTagRelDO;

import java.util.List;

public interface AdminArticleTagRelDao {
    void insertBatch(List<ArticleTagRelDO> articleTagRelDOS);

    List<ArticleTagRelDO> selectByArticleId(Long articleId);

    int deleteByArticleId(Long articleId);
}
