package com.quanxiaoha.weblog.web.dao;

import com.quanxiaoha.weblog.common.domain.dos.CategoryDO;

import java.util.List;

public interface CategoryDao {
    List<CategoryDO> selectAllCategory();

    CategoryDO selectByCategoryId(Long categoryId);
}
