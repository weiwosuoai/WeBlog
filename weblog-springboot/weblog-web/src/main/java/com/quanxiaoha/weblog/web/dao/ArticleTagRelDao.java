package com.quanxiaoha.weblog.web.dao;

import com.quanxiaoha.weblog.common.domain.dos.ArticleTagRelDO;

import java.util.List;

public interface ArticleTagRelDao {

    List<ArticleTagRelDO> selectByArticleIds(List<Long> articleIds);

    List<ArticleTagRelDO> selectByTagId(Long queryTagId);

    List<ArticleTagRelDO> selectByArticleId(Long articleId);
}
