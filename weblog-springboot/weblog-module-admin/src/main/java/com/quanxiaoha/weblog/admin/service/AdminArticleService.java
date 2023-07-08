package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.admin.model.vo.article.*;
import com.quanxiaoha.weblog.common.Response;

import java.util.List;

public interface AdminArticleService {
    Response publishArticle(PublishArticleReqVO publishArticleReqVO);

    Response queryArticleDetail(QueryArticleDetailReqVO queryArticleDetailReqVO);

    Response queryArticlePageList(QueryArticlePageListReqVO queryArticlePageListReqVO);

    Response deleteArticle(DeleteArticleReqVO deleteArticleReqVO);

    Response updateArticle(UpdateArticleReqVO updateArticleReqVO);

}
