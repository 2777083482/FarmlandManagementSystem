package com.fms.service.impl;

import com.fms.mapper.PlantingMapper;
import com.fms.pojo.dto.PlantingAddDo;
import com.fms.pojo.dto.PlantingPutDo;
import com.fms.pojo.entity.Planting;
import com.fms.pojo.vo.PlantingAllVo;
import com.fms.service.PlantingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantingServiceImpl implements PlantingService {

    @Autowired
    private PlantingMapper plantingMapper;

    // 根据农田ID查询种植信息
    @Override
    public List<PlantingAllVo> getPlantingsByFieldId(Integer fieldId) {
        return plantingMapper.findByFieldId(fieldId);
    }

    // 根据作物ID查询种植信息
    @Override
    public List<PlantingAllVo> getPlantingsByCropId(Integer cropId) {
        return plantingMapper.findByCropId(cropId);
    }

    // 添加种植记录
    @Override
    public void addPlanting(PlantingAddDo planting) {
        plantingMapper.insertPlanting(planting);
    }

    // 更新种植记录
    @Override
    public void updatePlanting(PlantingPutDo planting) {
        plantingMapper.updatePlanting(planting);
    }

    // 删除种植记录
    @Override
    public void deletePlanting(Integer id) {
        plantingMapper.deletePlanting(id);
    }
}
