package com.fms.service;

import com.fms.pojo.dto.PlantingAddDo;
import com.fms.pojo.dto.PlantingPutDo;
import com.fms.pojo.entity.Planting;
import com.fms.pojo.vo.PlantingAllVo;

import java.util.List;

public interface PlantingService {
    List<PlantingAllVo> getPlantingsByFieldId(Integer fieldId);
    List<PlantingAllVo> getPlantingsByCropId(Integer cropId);
    void addPlanting(PlantingAddDo planting);
    void updatePlanting(PlantingPutDo planting);
    void deletePlanting(Integer id);
    Planting getPlantingByFieldIdAndCropId(Integer fieldId, Integer cropId);

    List<PlantingAllVo> getPlantingsByUserId(Integer userId);
}
