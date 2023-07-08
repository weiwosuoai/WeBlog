package com.quanxiaoha.weblog.admin.dao;


import com.quanxiaoha.weblog.common.domain.dos.UserRoleDO;

import java.util.List;

public interface AdminUserRoleDao {
    List<UserRoleDO> selectByUsername(String username);
}
