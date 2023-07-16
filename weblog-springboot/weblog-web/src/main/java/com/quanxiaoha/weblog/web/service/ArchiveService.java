package com.quanxiaoha.weblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.domain.dos.ArticleDO;
import com.quanxiaoha.weblog.common.domain.dos.TagDO;
import com.quanxiaoha.weblog.web.model.vo.archive.QueryArchivePageListReqVO;


public interface ArchiveService extends IService<ArticleDO> {

    Response queryArchive(QueryArchivePageListReqVO queryArchivePageListReqVO);
}
