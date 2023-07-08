package com.quanxiaoha.weblog.admin.controller;

import com.quanxiaoha.weblog.admin.model.vo.tag.AddTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.QueryTagPageListReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.SearchTagReqVO;
import com.quanxiaoha.weblog.admin.service.AdminTagService;
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
@RequestMapping("/admin/tag")
public class AdminTagController {

    @Autowired
    private AdminTagService tagService;

    @PostMapping("/add")
    @ApiOperationLog(description = "新增标签")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response addTag(@RequestBody @Validated AddTagReqVO addTagReqVO) {
        return tagService.addTags(addTagReqVO);
    }

    @PostMapping("/list")
    @ApiOperationLog(description = "获取标签列表分页数据")
    public Response queryTagPageList(@RequestBody QueryTagPageListReqVO queryTagPageListReqVO) {
        return tagService.queryTagPageList(queryTagPageListReqVO);
    }

    @PostMapping("/delete")
    @ApiOperationLog(description = "删除标签")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response deleteTag(@RequestBody @Validated DeleteTagReqVO deleteTagReqVO) {
        return tagService.deleteTag(deleteTagReqVO);
    }

    @PostMapping("/search")
    @ApiOperationLog(description = "标签模糊查询")
    public Response searchTag(@RequestBody @Validated SearchTagReqVO searchTagReqVO) {
        return tagService.searchTags(searchTagReqVO);
    }

    @PostMapping("/select/list")
    @ApiOperationLog(description = "获取所有标签下拉框数据")
    public Response queryTagSelectList() {
        return tagService.queryTagSelectList();
    }

}
