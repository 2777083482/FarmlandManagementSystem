package com.fms.service.impl;

import com.fms.constant.CommonConstant;
import com.fms.exception.UserAddException;
import com.fms.exception.UserLoginException;
import com.fms.mapper.*;
import com.fms.pojo.dto.UserAddDo;
import com.fms.pojo.dto.UserLoginDo;
import com.fms.pojo.dto.UserPutDo;
import com.fms.pojo.entity.*;
import com.fms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CropMapper cropMapper;

    @Autowired
    private FieldMapper fieldMapper;

    @Override
    public User getUserById(int userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public void addUser(UserAddDo userAddDo) {
        String username = userAddDo.getUsername();
        String password = userAddDo.getPassword();
        if (username == null || password == null || username.equals("") || password.equals("")) {
            throw new UserAddException(CommonConstant.USERNAME_OR_PASSWORD_NULL);
        }
        UserLoginDo userLoginDo = new UserLoginDo(username, password);
        User user = userMapper.loginUser(userLoginDo);
        if (user != null) {
            //说明该用户已经被注册过了
            //报错不允许注册
            throw new UserAddException(CommonConstant.USER_EXIST);
        } else {
            userMapper.insertUser(userAddDo);
        }
    }

    @Override
    public void updateUser(UserPutDo userPutDo) {
        userMapper.updateUser(userPutDo);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteUser(int userId) {
        List<Field> fields = fieldMapper.findFieldsByUserId(userId);
        if (fields != null) {
            for (Field field : fields) {
                fieldMapper.deleteField(field.getFieldId(),userId);
            }
        }
        List<Crop> crops = cropMapper.findAllCrops(userId);
        if (crops != null) {
            for (Crop crop : crops) {
                cropMapper.deleteCrop(crop.getCropId(),userId);
            }
        }
        userMapper.deleteUser(userId);
    }

    @Override
    public User loginUser(UserLoginDo userLoginDo) {
        User user = null;
        try {
            user = userMapper.loginUser(userLoginDo);
            if (user == null) {
                throw new UserLoginException();
            }
        } catch (Exception e) {
            throw new UserLoginException("用户名或密码错误，请重新输入");
        }
        return user;
    }
}
