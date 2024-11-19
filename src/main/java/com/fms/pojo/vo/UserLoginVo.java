package com.fms.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginVo {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String token;
}
