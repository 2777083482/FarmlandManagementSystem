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
public class FertilizerRecordPutDo {
    private Integer fertilizerId;                  // 施肥记录ID
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fertilizerDate;              // 施肥日期
    private String fertilizerType;                 // 施肥种类
    private Double amount;                         // 施肥量（单位：公斤）
    private String remarks;                        // 备注
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;              // 更新时间
}
