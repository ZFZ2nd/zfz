package com.oracle.dao;

import com.oracle.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    public List<Role> findRoleByUserId(String userId) throws Exception;
}
