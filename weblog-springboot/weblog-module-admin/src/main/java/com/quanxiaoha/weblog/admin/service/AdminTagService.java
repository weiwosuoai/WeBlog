package com.quanxiaoha.weblog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanxiaoha.weblog.admin.model.vo.tag.SearchTagReqVO;
import com.quanxiaoha.weblog.common.domain.dos.TagDO;
import com.quanxiaoha.weblog.admin.model.vo.tag.AddTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.quanxiaoha.weblog.admin.model.vo.tag.QueryTagPageListReqVO;
import com.quanxiaoha.weblog.common.Response;


public interface AdminTagService extends IService<TagDO> {
    Response addTags(AddTagReqVO addTagReqVO);

    Response queryTagPageList(QueryTagPageListReqVO queryTagPageListReqVO);

    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    Response searchTags(SearchTagReqVO searchTagReqVO);

    Response queryTagSelectList();
}
