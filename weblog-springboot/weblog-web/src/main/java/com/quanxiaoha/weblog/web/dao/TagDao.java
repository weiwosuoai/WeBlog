package com.quanxiaoha.weblog.web.dao;

import com.quanxiaoha.weblog.common.domain.dos.TagDO;

import java.util.List;

public interface TagDao {
    List<TagDO> selectAllTag();

    List<TagDO> selectByTagIds(List<Long> tagIds);
}
