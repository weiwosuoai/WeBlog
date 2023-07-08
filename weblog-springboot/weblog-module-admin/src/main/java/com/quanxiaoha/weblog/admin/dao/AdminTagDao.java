package com.quanxiaoha.weblog.admin.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanxiaoha.weblog.common.domain.dos.TagDO;

import java.util.Date;
import java.util.List;

public interface AdminTagDao {
    Page<TagDO> queryTagPageList(Long current, Long size, Date startDate, Date endDate, String tagName);

    List<TagDO> searchTags(String key);

    List<TagDO> selectAll();

    int insert(TagDO tagDO);

    Long selectTotalCount();
}
