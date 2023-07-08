package com.quanxiaoha.weblog.web.controller;

import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import com.quanxiaoha.weblog.admin.model.vo.blogsetting.UpdateBlogSettingReqVO;
import com.quanxiaoha.weblog.web.service.BlogSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-18 8:14
 * @description: 博客设置
 **/
@RestController
@RequestMapping("/blog/setting")
public class BlogSettingController {

    @Autowired
    private BlogSettingService blogSettingService;

    @PostMapping("/detail")
    @ApiOperationLog(description = "获取右边栏博客详情信息")
    public Response queryBlogSettingDetail() {
        return blogSettingService.queryBlogSettingDetail();
    }

}
