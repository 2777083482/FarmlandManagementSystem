package com.fms.service;

import com.fms.pojo.dto.CropAddDo;
import com.fms.pojo.dto.CropPutDo;
import com.fms.pojo.entity.Crop;

import java.util.List;

public interface CropService {
    List<Crop> getAllCrops(Integer userId);
    Crop getCropById(Integer cropId);
    void addCrop(CropAddDo crop);
    void updateCrop(CropPutDo crop);
    void deleteCrop(Integer cropId,Integer userId);
}
