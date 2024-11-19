package com.fms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPutDo {
    private Integer userId;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime updateTime;
}
