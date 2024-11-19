package com.fms.service.impl;

import com.fms.annotation.AutoFill;
import com.fms.enumeration.OperationType;
import com.fms.pojo.dto.UserAddDo;
import com.fms.pojo.dto.UserLoginDo;
import com.fms.pojo.dto.UserPutDo;
import com.fms.pojo.entity.User;
import com.fms.mapper.UserMapper;
import com.fms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(int userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public void addUser(UserAddDo userAddDo) {
        userMapper.insertUser(userAddDo);
    }

    @Override
    public void updateUser(UserPutDo userPutDo) {
        userMapper.updateUser(userPutDo);
    }

    @Override
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public User loginUser(UserLoginDo userLoginDo) {
        User user = userMapper.loginUser(userLoginDo);
        return user;
    }
}
