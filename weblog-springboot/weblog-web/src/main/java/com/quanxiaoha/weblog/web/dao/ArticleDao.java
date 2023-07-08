package com.quanxiaoha.weblog.web.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.quanxiaoha.weblog.common.domain.dos.ArticleDO;

import java.util.List;

public interface ArticleDao {
    IPage<ArticleDO> queryArticlePageList(long current, long size);

    ArticleDO selectArticleById(Long articleId);

    ArticleDO selectPreArticle(Long articleId);

    ArticleDO selectNextArticle(Long articleId);

    IPage<ArticleDO> queryArticlePageListByArticleIds(Long current, Long size, List<Long> articleIds);
}
