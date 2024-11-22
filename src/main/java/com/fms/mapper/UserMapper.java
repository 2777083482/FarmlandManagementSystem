package com.fms.mapper;

import com.fms.annotation.AutoFill;
import com.fms.enumeration.OperationType;
import com.fms.pojo.dto.UserAddDo;
import com.fms.pojo.dto.UserLoginDo;
import com.fms.pojo.dto.UserPutDo;
import com.fms.pojo.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    // 根据 ID 查询用户
    @Select("SELECT * FROM users WHERE user_id = #{userId}")
    User findUserById(@Param("userId") int userId);

    // 插入用户
    @Insert("INSERT INTO users (username, password, email, phone, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{email}, #{phone}, #{createTime}, #{updateTime},#{create})")
    @AutoFill(OperationType.INSERT)
    void insertUser(UserAddDo userAddDo);

    // 更新用户
    @AutoFill(OperationType.UPDATE)
    void updateUser(UserPutDo userPutDo);

    // 删除用户
    @Delete("DELETE FROM users WHERE user_id = #{userId}")
    void deleteUser(@Param("userId") int userId);

    //登录用户
    @Select("select * from users where username = #{username} and password = #{password}")
    User loginUser(UserLoginDo userLoginDo);
}

