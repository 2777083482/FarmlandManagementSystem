package com.fms.controller;

import com.fms.annotation.UserIdCheck;
import com.fms.context.BaseContext;
import com.fms.pojo.dto.FieldAddDo;
import com.fms.pojo.dto.FieldPutDo;
import com.fms.pojo.entity.Field;
import com.fms.result.Result;
import com.fms.service.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/fields")
@Slf4j
public class FieldController {
    @Autowired
    private FieldService fieldService;

    // 根据用户ID获取农田列表
    @GetMapping("/getFields/{id}")
    @UserIdCheck
    public Result<List<Field>> getFields(@PathVariable("id") Integer id) {
        Integer userId = BaseContext.getCurrentId();
        if (!Objects.equals(userId, id)) {

        }
        log.info("获取用户ID为{}的农田列表", userId);
        List<Field> fields = fieldService.getFieldsByUserId(userId);
        return Result.success(fields);
    }

    // 添加农田
    @PostMapping("/addField")
    @UserIdCheck
    public Result addField(@RequestBody FieldAddDo fieldAddDo) {
        log.info("添加农田");
        fieldService.addField(fieldAddDo);
        return Result.success();
    }

    // 更新农田
    @PutMapping("/updateField")
    @UserIdCheck
    public Result updateField(@RequestBody FieldPutDo fieldPutDo) {
        log.info("更新农田，用户ID为{}", fieldPutDo.getUserId());
        fieldService.updateField(fieldPutDo);
        return Result.success();
    }

    // 删除农田
    @DeleteMapping("/deleteField/{id}")
    public Result deleteField(@PathVariable("id") Integer fieldId) {
        Integer userId = BaseContext.getCurrentId();
        log.info("删除农田，农田ID为{}，用户ID为{}", fieldId, userId);
        fieldService.deleteField(fieldId, userId);
        return Result.success();
    }
}
