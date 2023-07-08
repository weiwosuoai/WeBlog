package com.quanxiaoha.weblog.admin.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanxiaoha.weblog.common.domain.dos.ArticleCountDO;
import com.quanxiaoha.weblog.common.domain.dos.ArticleDO;

import java.util.Date;
import java.util.List;

public interface AdminArticleDao {
    int insertArticle(ArticleDO articleDO);

    ArticleDO queryByArticleId(Long articleId);

    Page<ArticleDO> queryArticlePageList(Long current, Long size, Date startDate, Date endDate, String searchTitle);

    int deleteById(Long articleId);

    int updateById(ArticleDO articleDO);

    Long selectTotalCount();

    List<ArticleCountDO> selectArticleCount(String startDate, String endDate);

    int readNumIncrease(Long articleId);
}
