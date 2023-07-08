package com.quanxiaoha.weblog.web.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanxiaoha.weblog.common.domain.mapper.ArticleMapper;
import com.quanxiaoha.weblog.common.domain.dos.ArticleDO;
import com.quanxiaoha.weblog.web.dao.ArticleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-17 12:08
 * @description: TODO
 **/
@Service
@Slf4j
public class ArticleDaoImpl implements ArticleDao {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public IPage<ArticleDO> queryArticlePageList(long current, long size) {
        Page<ArticleDO> page = new Page<>(current, size);
        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(ArticleDO::getCreateTime);
        return articleMapper.selectPage(page, wrapper);
    }

    @Override
    public ArticleDO selectArticleById(Long articleId) {
        return articleMapper.selectById(articleId);
    }

    @Override
    public ArticleDO selectPreArticle(Long articleId) {
        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByAsc(ArticleDO::getId).gt(ArticleDO::getId, articleId).last("limit 1");
        return articleMapper.selectOne(wrapper);
    }

    @Override
    public ArticleDO selectNextArticle(Long articleId) {
        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(ArticleDO::getId).lt(ArticleDO::getId, articleId).last("limit 1");
        return articleMapper.selectOne(wrapper);
    }

    @Override
    public IPage<ArticleDO> queryArticlePageListByArticleIds(Long current, Long size, List<Long> articleIds) {
        Page<ArticleDO> page = new Page<>(current, size);
        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(ArticleDO::getId, articleIds).orderByDesc(ArticleDO::getCreateTime);
        return articleMapper.selectPage(page, wrapper);
    }
}
