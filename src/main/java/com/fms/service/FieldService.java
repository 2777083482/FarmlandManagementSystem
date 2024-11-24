package com.fms.service;

import com.fms.pojo.dto.FieldAddDo;
import com.fms.pojo.dto.FieldPutDo;
import com.fms.pojo.entity.Field;

import java.util.List;

public interface FieldService {
    List<Field> getFieldsByUserId(Integer userId);
    void addField(FieldAddDo fieldAddDo);
    void updateField(FieldPutDo fieldPutDo);
    void deleteField(Integer fieldId, Integer userId);
}

