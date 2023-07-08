package com.quanxiaoha.weblog.admin.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quanxiaoha.weblog.admin.dao.AdminUserRoleDao;
import com.quanxiaoha.weblog.common.domain.dos.UserRoleDO;
import com.quanxiaoha.weblog.common.domain.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-17 12:08
 * @description: TODO
 **/
@Service
@Slf4j
public class AdminUserRoleDaoImpl implements AdminUserRoleDao {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRoleDO> selectByUsername(String username) {
        QueryWrapper<UserRoleDO> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserRoleDO::getUsername, username);
        return userRoleMapper.selectList(wrapper);
    }
}
