package com.quanxiaoha.weblog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanxiaoha.weblog.common.domain.dos.CategoryDO;
import com.quanxiaoha.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.quanxiaoha.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.quanxiaoha.weblog.admin.model.vo.category.QueryCategoryPageListReqVO;
import com.quanxiaoha.weblog.common.PageResponse;
import com.quanxiaoha.weblog.common.Response;


public interface AdminCategoryService extends IService<CategoryDO> {
    Response addCategory(AddCategoryReqVO addCategoryReqVO);

    PageResponse queryCategoryPageList(QueryCategoryPageListReqVO queryCategoryPageListReqVO);

    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);

    Response queryCategorySelectList();
}
