package com.fms.service.impl;

import com.fms.mapper.FieldMapper;
import com.fms.pojo.dto.FieldAddDo;
import com.fms.pojo.dto.FieldPutDo;
import com.fms.pojo.entity.Field;
import com.fms.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldMapper fieldMapper;

    @Override
    public List<Field> getFieldsByUserId(int userId) {
        return fieldMapper.findFieldsByUserId(userId);
    }

    @Override
    public void addField(FieldAddDo fieldAddDo) {
        fieldMapper.insertField(fieldAddDo);
    }

    @Override
    public void updateField(FieldPutDo fieldPutDo) {
        fieldMapper.updateField(fieldPutDo);
    }

    @Override
    public void deleteField(int fieldId, int userId) {
        fieldMapper.deleteField(fieldId, userId);
    }
}
