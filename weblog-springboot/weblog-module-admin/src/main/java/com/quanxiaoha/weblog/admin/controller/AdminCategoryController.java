package com.quanxiaoha.weblog.admin.controller;

import com.quanxiaoha.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.quanxiaoha.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.quanxiaoha.weblog.admin.model.vo.category.QueryCategoryPageListReqVO;
import com.quanxiaoha.weblog.admin.service.AdminCategoryService;
import com.quanxiaoha.weblog.common.PageResponse;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-19 16:06
 * @description: TODO
 **/
@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private AdminCategoryService categoryService;

    @PostMapping("/add")
    @ApiOperationLog(description = "新增分类")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
        return categoryService.addCategory(addCategoryReqVO);
    }

    @PostMapping("/list")
    @ApiOperationLog(description = "获取分类列表分页数据")
    public PageResponse queryCategoryPageList(@RequestBody QueryCategoryPageListReqVO queryCategoryPageListReqVO) {
        return categoryService.queryCategoryPageList(queryCategoryPageListReqVO);
    }

    @PostMapping("/delete")
    @ApiOperationLog(description = "删除分类")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteCategory(@RequestBody @Validated DeleteCategoryReqVO deleteCategoryReqVO) {
        return categoryService.deleteCategory(deleteCategoryReqVO);
    }

    @PostMapping("/select/list")
    @ApiOperationLog(description = "获取所有分类下拉框数据")
    public Response queryCategorySelectList() {
        return categoryService.queryCategorySelectList();
    }
}
