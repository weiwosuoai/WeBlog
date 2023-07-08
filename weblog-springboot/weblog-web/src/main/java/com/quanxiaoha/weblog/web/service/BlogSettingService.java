package com.quanxiaoha.weblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.domain.dos.BlogSettingDO;


public interface BlogSettingService extends IService<BlogSettingDO> {

    Response queryBlogSettingDetail();
}
