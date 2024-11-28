package com.fms.controller;
import com.fms.pojo.dto.IrrigationRecordAddDo;
import com.fms.pojo.dto.IrrigationRecordPutDo;
import com.fms.pojo.entity.IrrigationRecord;
import com.fms.result.Result;
import com.fms.service.IrrigationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/irrigationRecords")
@Slf4j
public class IrrigationRecordController {

    @Autowired
    private IrrigationRecordService irrigationRecordService;

    // 获取某种植记录的灌溉信息
    @GetMapping("/planting/{plantingsId}")
    public Result<List<IrrigationRecord>> getIrrigationRecordsByPlantingsId(@PathVariable("plantingsId") Integer plantingsId) {
        log.info("查询种植记录ID {} 的灌溉信息", plantingsId);
        List<IrrigationRecord> records = irrigationRecordService.getIrrigationRecordsByPlantingsId(plantingsId);
        return Result.success(records);
    }

    @GetMapping("/irrigation/{irrigationId}")
    public Result<List<IrrigationRecord>> getIrrigationRecordsByIrrigationId(@PathVariable("irrigationId") Integer irrigationId) {
        log.info("查询灌溉ID {} 的灌溉信息", irrigationId);
        List<IrrigationRecord> records = irrigationRecordService.getIrrigationRecordsByIrrigationId(irrigationId);
        return Result.success(records);
    }

    // 添加灌溉记录
    @PostMapping("/addIrrigationRecords")
    public Result addIrrigationRecord(@RequestBody IrrigationRecordAddDo irrigationRecord) {
        log.info("添加灌溉记录 {}", irrigationRecord);
        irrigationRecordService.addIrrigationRecord(irrigationRecord);
        return Result.success("灌溉记录添加成功");
    }

    // 更新灌溉记录
    @PutMapping("/updateIrrigationRecords")
    public Result updateIrrigationRecord(@RequestBody IrrigationRecordPutDo irrigationRecord) {
        log.info("更新灌溉记录ID {} -> {}", irrigationRecord.getIrrigationId(), irrigationRecord);
        irrigationRecordService.updateIrrigationRecord(irrigationRecord);
        return Result.success("灌溉记录更新成功");
    }

    // 删除灌溉记录
    @DeleteMapping("/deleteIrrigationRecords/{id}")
    public Result deleteIrrigationRecord(@PathVariable("id") Integer id) {
        log.info("删除灌溉记录ID {}", id);
        irrigationRecordService.deleteIrrigationRecord(id);
        return Result.success("灌溉记录删除成功");
    }
}

