package com.quanxiaoha.weblog.admin.dao;

import com.quanxiaoha.weblog.common.domain.dos.CategoryDO;

import java.util.List;

public interface AdminCategoryDao {
    List<CategoryDO> selectAllCategory();

    Long selectTotalCount();
}
