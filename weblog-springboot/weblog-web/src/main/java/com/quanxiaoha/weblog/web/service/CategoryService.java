package com.quanxiaoha.weblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.domain.dos.CategoryDO;


public interface CategoryService extends IService<CategoryDO> {
    Response queryCategoryList();

}
