package com.fms.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantingAllVo {
    private Integer id;                             // 种植记录ID
    private String fieldName;                       // 农田名称
    private String cropName;                        // 作物名称
    private String growthStatus;                    // 生长状况
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate plantingDate;             //播种日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate harvestDate;              //收割日期
}
