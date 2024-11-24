package com.fms.service;

import com.fms.pojo.entity.Planting;

import java.util.List;

public interface PlantingService {
    List<Planting> getPlantingsByFieldId(Integer fieldId);
    List<Planting> getPlantingsByCropId(Integer cropId);
    void addPlanting(Planting planting);
    void updatePlanting(Planting planting);
    void deletePlanting(Integer id);
}
