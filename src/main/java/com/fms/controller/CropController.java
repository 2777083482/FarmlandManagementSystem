package com.fms.controller;

import com.fms.annotation.UserIdCheck;
import com.fms.context.BaseContext;
import com.fms.pojo.dto.CropAddDo;
import com.fms.pojo.dto.CropPutDo;
import com.fms.pojo.entity.Crop;
import com.fms.result.Result;
import com.fms.service.CropService;
import com.fms.util.ThreadUserIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crops")
@Slf4j
public class CropController {
    @Autowired
    private CropService cropService;

    // 查询所有作物
    @GetMapping("/getCrops/{userId}")
    @UserIdCheck
    public Result<List<Crop>> getAllCrops(@PathVariable("userId") Integer userId) {
        log.info("查询用户的所有作物信息，用户id为：{}",userId);
        List<Crop> crops = cropService.getAllCrops(userId);
        if (crops.isEmpty()) {
            log.warn("未找到任何作物信息");
            return Result.error("您并未添加任何作物记录，请添加");
        }
        return Result.success(crops);
    }

    // 根据作物ID查询作物
    @GetMapping("/getCropsByCropById/{id}")
    public Result<Crop> getCropById(@PathVariable("id") Integer id) {
        log.info("查询作物ID为 {} 的信息", id);
        Crop crop = cropService.getCropById(id);
        if (crop == null) {
            log.warn("作物ID {} 不存在", id);
            return Result.error("作物不存在");
        }
        return Result.success(crop);
    }

    // 添加作物
    @PostMapping("/addCrop")
    @UserIdCheck
    public Result addCrop(@RequestBody CropAddDo crop) {
        log.info("添加作物信息 {}", crop);
        cropService.addCrop(crop);
        return Result.success("作物添加成功");
    }

    // 更新作物
    @PutMapping("/updateCrop/{id}")
    @UserIdCheck
    public Result updateCrop(@PathVariable("id") Integer id, @RequestBody CropPutDo crop) {
        log.info("更新作物ID {} 信息 -> {}", id, crop);
        crop.setCropId(id);
        cropService.updateCrop(crop);
        return Result.success("作物更新成功");
    }

    // 删除作物
    @DeleteMapping("/deleteCrop/{id}")
    public Result deleteCrop(@PathVariable("id") Integer id) {
        Integer userId = BaseContext.getCurrentId();
        log.info("删除’id为 {} 的用户‘的’ID为 {} 的作物",userId,id);
        cropService.deleteCrop(id,userId);
        return Result.success("作物删除成功");
    }
}


