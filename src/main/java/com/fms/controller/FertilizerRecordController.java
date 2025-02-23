package com.fms.controller;

import com.fms.pojo.dto.FertilizerRecordAddDo;
import com.fms.pojo.dto.FertilizerRecordPutDo;
import com.fms.pojo.entity.FertilizerRecord;
import com.fms.result.Result;
import com.fms.service.FertilizerRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fertilizerRecords")
@Slf4j
public class FertilizerRecordController {

    @Autowired
    private FertilizerRecordService fertilizerRecordService;

    // 获取某种植记录的施肥信息
    @GetMapping("/planting/{plantingsId}")
    public Result<List<FertilizerRecord>> getFertilizerRecordsByPlantingsId(@PathVariable("plantingsId") Integer plantingsId) {
        log.info("查询种植记录ID {} 的施肥信息", plantingsId);
        List<FertilizerRecord> records = fertilizerRecordService.getFertilizerRecordsByPlantingsId(plantingsId);
        return Result.success(records);
    }

    // 获取某个用户的今天的施肥记录
    @GetMapping("/Fertilizer/today/{userId}")
    public Result<List<FertilizerRecord>> getTodayFertilizerRecordsByUserId(@PathVariable("userId") Integer userId) {
        log.info("查询用户ID {} 的施肥信息", userId);
        List<FertilizerRecord> records = fertilizerRecordService.getTodayFertilizerRecordsByUserId(userId);
        return Result.success(records);
    }

    @GetMapping("/Fertilizer/{FertilizerId}")
    public Result<List<FertilizerRecord>> getFertilizerRecordsByFertilizerId(@PathVariable("FertilizerId") Integer FertilizerId) {
        log.info("查询施肥ID {} 的施肥信息", FertilizerId);
        List<FertilizerRecord> records = fertilizerRecordService.getFertilizerRecordsByFertilizerId(FertilizerId);
        return Result.success(records);
    }

    // 添加施肥记录
    @PostMapping("/addFertilizerRecords")
    public Result addFertilizerRecord(@RequestBody FertilizerRecordAddDo fertilizerRecord) {
        log.info("添加施肥记录 {}", fertilizerRecord);
        fertilizerRecordService.addFertilizerRecord(fertilizerRecord);
        return Result.success("施肥记录添加成功");
    }

    // 更新施肥记录
    @PutMapping("/updateFertilizerRecords")
    public Result updateFertilizerRecord(@RequestBody FertilizerRecordPutDo fertilizerRecord) {
        log.info("更新施肥记录ID {} -> {}", fertilizerRecord.getFertilizerId(), fertilizerRecord);
        fertilizerRecordService.updateFertilizerRecord(fertilizerRecord);
        return Result.success("施肥记录更新成功");
    }

    // 删除施肥记录
    @DeleteMapping("/deleteFertilizerRecords/{id}")
    public Result deleteFertilizerRecord(@PathVariable("id") Integer id) {
        log.info("删除施肥记录ID {}", id);
        fertilizerRecordService.deleteFertilizerRecord(id);
        return Result.success("施肥记录删除成功");
    }
}

