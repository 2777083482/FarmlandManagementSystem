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
public class IrrigationRecordPutDo {
    private Integer irrigationId;                  // 灌溉记录ID
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate irrigationDate;              // 灌溉日期
    private Double amount;                         // 灌溉水量（单位：吨）
    private String remarks;                        // 备注
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;              // 更新时间
}
