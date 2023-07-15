package com.quanxiaoha.weblog.web.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanxiaoha.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.quanxiaoha.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.quanxiaoha.weblog.web.dao.ArticleCategoryRelDao;
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
public class ArticleCategoryRelDaoImpl implements ArticleCategoryRelDao {

    @Autowired
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    @Override
    public List<ArticleCategoryRelDO> selectByArticleIds(List<Long> articleIds) {
        QueryWrapper<ArticleCategoryRelDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(ArticleCategoryRelDO::getArticleId, articleIds);
        return articleCategoryRelMapper.selectList(wrapper);
    }

    @Override
    public List<ArticleCategoryRelDO> selectByCategoryId(Long categoryId) {
        QueryWrapper<ArticleCategoryRelDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(ArticleCategoryRelDO::getCategoryId, categoryId);
        return articleCategoryRelMapper.selectList(wrapper);
    }

    @Override
    public ArticleCategoryRelDO selectByArticleId(Long articleId) {
        QueryWrapper<ArticleCategoryRelDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ArticleCategoryRelDO::getArticleId, articleId);
        return articleCategoryRelMapper.selectOne(wrapper);
    }
}
