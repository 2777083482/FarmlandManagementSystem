package com.fms.pojo.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IrrigationRecord {
    private Integer irrigationId;                  // 灌溉记录ID
    private Integer plantingsId;                   // 种植ID
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate irrigationDate;              // 灌溉日期
    private Double amount;                         // 灌溉水量（单位：吨）
    private String remarks;                        // 备注
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;              // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;              // 更新时间
}
