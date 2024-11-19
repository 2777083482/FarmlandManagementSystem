package com.fms.controller;

import com.context.BaseContext;
import com.fms.constant.CommonConstant;
import com.fms.constant.JWTConstant;
import com.fms.pojo.dto.UserAddDo;
import com.fms.pojo.dto.UserLoginDo;
import com.fms.pojo.dto.UserPutDo;
import com.fms.pojo.entity.User;
import com.fms.pojo.vo.UserLoginVo;
import com.fms.result.Result;
import com.fms.service.UserService;
import com.fms.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    // 根据 ID 获取用户
    @GetMapping("/getUser/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer userId) {
        log.info("获取用户，id为{}",userId);
        User user = userService.getUserById(userId);
        return Result.success(user);
    }

    // 添加用户
    @PostMapping("/addUser")
    public Result addUser(@RequestBody UserAddDo userAddDo) {
        log.info("添加用户，用户信息：{}", userAddDo.toString());
        userService.addUser(userAddDo);
        return Result.success();
    }

    // 登录用户
    @PostMapping("/login")
    public Result<UserLoginVo> loginUser(@RequestBody UserLoginDo userLoginDo) {
        log.info("用户登录");
        User user = userService.loginUser(userLoginDo);
        Map<String,Object> claims = new HashMap<>();
        claims.put(JWTConstant.USERID,user.getUserId());
        String token = JwtUtil.generateToken(claims);
        UserLoginVo userLoginVo = new UserLoginVo();
        BeanUtils.copyProperties(user,userLoginVo);
        userLoginVo.setToken(token);
        return Result.success(userLoginVo);
    }

    // 更新用户
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody UserPutDo userPutDo) {
        log.info("更新用户，用户信息：{}",userPutDo.toString());
        Integer threadUserId = BaseContext.getCurrentId();
        if (!Objects.equals(threadUserId, userPutDo.getUserId())) {
            return Result.error(CommonConstant.USERID_ONT_MATCHED);
        }
        userService.updateUser(userPutDo);
        return Result.success();
    }

    // 删除用户
    @DeleteMapping("/delete/{id}")
    public Result deleteUser(@PathVariable("id") Integer userId) {
        log.info("删除用户，id为{}",userId);
        Integer threadUserId = BaseContext.getCurrentId();
        if (!Objects.equals(threadUserId, userId)) {
            return Result.error(CommonConstant.USERID_ONT_MATCHED);
        }
        userService.deleteUser(userId);
        return Result.success();
    }
}

