package com.fms.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldAddDo {
    private String name;
    private Double area;
    private String location;
    private String soilType;
    private String cropType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String plantingDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String harvestDate;
    private Integer userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}