package com.fms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldPutDo {
    private Integer fieldId;                       // 农田ID
    private String name;                           // 农田名称
    private Double area;                           // 农田面积
    private String location;                       // 农田位置
    private String soilType;                       // 土壤类型
    private Integer userId;                        // 用户ID
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;             // 更新时间
}

