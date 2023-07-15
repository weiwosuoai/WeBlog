package com.quanxiaoha.weblog.web.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanxiaoha.weblog.common.domain.mapper.TagMapper;
import com.quanxiaoha.weblog.common.domain.dos.TagDO;
import com.quanxiaoha.weblog.web.dao.TagDao;
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
public class TagDaoImpl implements TagDao {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagDO> selectAllTag() {
        return tagMapper.selectList(null);
    }

    @Override
    public List<TagDO> selectByTagIds(List<Long> tagIds) {
        QueryWrapper<TagDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(TagDO::getId, tagIds);
        return tagMapper.selectList(wrapper);
    }
}
