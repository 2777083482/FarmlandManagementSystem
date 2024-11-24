package com.fms.controller;

import com.fms.pojo.entity.Planting;
import com.fms.result.Result;
import com.fms.service.PlantingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantings")
@Slf4j
public class PlantingController {
    //TODO
    //@Autowired
    private PlantingService plantingService;

    // 获取某农田的种植信息
    @GetMapping("/field/{fieldId}")
    public Result<List<Planting>> getPlantingsByFieldId(@PathVariable("fieldId") Integer fieldId) {
        log.info("查询农田ID {} 的种植信息", fieldId);
        List<Planting> plantings = plantingService.getPlantingsByFieldId(fieldId);
        return Result.success(plantings);
    }

    // 获取某作物的种植信息
    @GetMapping("/crop/{cropId}")
    public Result<List<Planting>> getPlantingsByCropId(@PathVariable("cropId") Integer cropId) {
        log.info("查询作物ID {} 的种植信息", cropId);
        List<Planting> plantings = plantingService.getPlantingsByCropId(cropId);
        return Result.success(plantings);
    }

    // 添加种植记录
    @PostMapping
    public Result addPlanting(@RequestBody Planting planting) {
        log.info("添加种植记录 {}", planting);
        plantingService.addPlanting(planting);
        return Result.success();
    }

    // 更新种植记录
    @PutMapping
    public Result updatePlanting(@RequestBody Planting planting) {
        log.info("更新种植记录 ID {} -> {}", planting.getId(), planting);
        plantingService.updatePlanting(planting);
        return Result.success();
    }

    // 删除种植记录
    @DeleteMapping("/{id}")
    public Result deletePlanting(@PathVariable("id") Integer id) {
        log.info("删除种植记录 ID {}", id);
        plantingService.deletePlanting(id);
        return Result.success();
    }
}

