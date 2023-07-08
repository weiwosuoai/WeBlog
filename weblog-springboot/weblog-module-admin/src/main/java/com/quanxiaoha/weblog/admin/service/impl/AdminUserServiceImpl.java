package com.quanxiaoha.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanxiaoha.weblog.admin.dao.AdminUserDao;
import com.quanxiaoha.weblog.admin.model.vo.user.UpdateAdminPasswordReqVO;
import com.quanxiaoha.weblog.admin.service.AdminUserService;
import com.quanxiaoha.weblog.common.Response;
import com.quanxiaoha.weblog.common.domain.dos.UserDO;
import com.quanxiaoha.weblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-17 12:08
 * @description: TODO
 **/
@Service
@Slf4j
public class AdminUserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements AdminUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminUserDao userDao;

    @Override
    public Response updateAdminPassword(UpdateAdminPasswordReqVO updateAdminPasswordReqVO) {
        UserDO userDO = UserDO.builder()
                .password(passwordEncoder.encode(updateAdminPasswordReqVO.getNewPassword()))
                .updateTime(new Date())
                .build();
        userDao.updateAdminPassword(userDO);
        return Response.success();
    }
}
