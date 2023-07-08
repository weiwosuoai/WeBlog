package com.quanxiaoha.weblog.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanxiaoha.weblog.admin.model.vo.blogsetting.UpdateBlogSettingReqVO;
import com.quanxiaoha.weblog.admin.model.vo.user.QueryUserDetailRspVO;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.domain.dos.BlogSettingDO;


public interface AdminBlogSettingService extends IService<BlogSettingDO> {

    Response updateBlogSetting(UpdateBlogSettingReqVO updateBlogSettingReqVO);

    Response queryBlogSettingDetail();

    Response<QueryUserDetailRspVO> queryNicknameAndAvatar();
}
