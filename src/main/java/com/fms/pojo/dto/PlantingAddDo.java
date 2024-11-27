package com.fms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantingAddDo {
    private Integer fieldId;                       // 农田ID
    private Integer cropId;                        // 作物ID
    private String growthStatus;                   // 生长状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate plantingDate;                // 种植日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate harvestDate;                 // 收获日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;              // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;              // 更新时间
}
