package com.fms.service.impl;

import com.fms.exception.CommonException;
import com.fms.mapper.FieldMapper;
import com.fms.mapper.PlantingMapper;
import com.fms.pojo.dto.FieldAddDo;
import com.fms.pojo.dto.FieldPutDo;
import com.fms.pojo.entity.Field;
import com.fms.pojo.vo.PlantingAllVo;
import com.fms.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldMapper fieldMapper;

    @Autowired
    private PlantingMapper plantingMapper;
    @Override
    public List<Field> getFieldsByUserId(Integer userId) {
        return fieldMapper.findFieldsByUserId(userId);
    }

    @Override
    public void addField(FieldAddDo fieldAddDo) {
        String name = fieldAddDo.getName();
        if (name == null || name.trim().equals("")) {
            throw new CommonException("农田名称不许为空");
        }
        fieldMapper.insertField(fieldAddDo);
    }

    @Override
    public void updateField(FieldPutDo fieldPutDo) {
        String name = fieldPutDo.getName();
        if (name == null || name.trim().equals("")) {
            throw new CommonException("农田名称不许为空");
        }
        fieldMapper.updateField(fieldPutDo);
    }

    @Override
    @Transactional
    public void deleteField(Integer fieldId, Integer userId) {
        List<PlantingAllVo> plantings = plantingMapper.findByFieldId(fieldId);
        if (plantings != null) {
            for (PlantingAllVo planting : plantings) {
                plantingMapper.deletePlanting(planting.getId());
            }
        }
        fieldMapper.deleteField(fieldId, userId);
    }
}
