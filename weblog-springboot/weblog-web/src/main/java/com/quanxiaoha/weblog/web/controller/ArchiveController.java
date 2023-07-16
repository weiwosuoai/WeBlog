package com.quanxiaoha.weblog.web.controller;

import com.quanxiaoha.weblog.common.PageResponse;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import com.quanxiaoha.weblog.web.model.vo.archive.QueryArchivePageListReqVO;
import com.quanxiaoha.weblog.web.model.vo.article.QueryIndexArticlePageListReqVO;
import com.quanxiaoha.weblog.web.model.vo.article.QueryTagArticlePageListReqVO;
import com.quanxiaoha.weblog.web.service.ArchiveService;
import com.quanxiaoha.weblog.web.service.ArticleService;
import com.quanxiaoha.weblog.web.service.TagService;
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
 * @description: 归档
 **/
@RestController
@RequestMapping("/archive")
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;

    @PostMapping("/list")
    @ApiOperationLog(description = "获取文章归档列表")
    public Response queryArchive(@RequestBody QueryArchivePageListReqVO queryArchivePageListReqVO) {
        return archiveService.queryArchive(queryArchivePageListReqVO);
    }

}
