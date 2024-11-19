package com.fms.service;

import com.fms.pojo.dto.UserAddDo;
import com.fms.pojo.dto.UserLoginDo;
import com.fms.pojo.dto.UserPutDo;
import com.fms.pojo.entity.User;

public interface UserService {
    User getUserById(int userId);
    void addUser(UserAddDo userAddDo);
    void updateUser(UserPutDo userPutDo);
    void deleteUser(int userId);
    User loginUser(UserLoginDo userLoginDo);
}
