package com.fms.controller;

import com.fms.pojo.entity.FertilizerRecord;
import com.fms.result.Result;
import com.fms.service.FertilizerRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fertilizer-records")
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

    // 添加施肥记录
    @PostMapping
    public Result addFertilizerRecord(@RequestBody FertilizerRecord fertilizerRecord) {
        log.info("添加施肥记录 {}", fertilizerRecord);
        fertilizerRecordService.addFertilizerRecord(fertilizerRecord);
        return Result.success("施肥记录添加成功");
    }

    // 更新施肥记录
    @PutMapping("/{id}")
    public Result updateFertilizerRecord(@PathVariable("id") Integer id, @RequestBody FertilizerRecord fertilizerRecord) {
        log.info("更新施肥记录ID {} -> {}", id, fertilizerRecord);
        fertilizerRecord.setFertilizerId(id);
        fertilizerRecordService.updateFertilizerRecord(fertilizerRecord);
        return Result.success("施肥记录更新成功");
    }

    // 删除施肥记录
    @DeleteMapping("/{id}")
    public Result deleteFertilizerRecord(@PathVariable("id") Integer id) {
        log.info("删除施肥记录ID {}", id);
        fertilizerRecordService.deleteFertilizerRecord(id);
        return Result.success("施肥记录删除成功");
    }
}

