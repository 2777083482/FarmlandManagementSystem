package com.fms.service.impl;

import com.fms.mapper.CropMapper;
import com.fms.pojo.dto.CropAddDo;
import com.fms.pojo.dto.CropPutDo;
import com.fms.pojo.entity.Crop;
import com.fms.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作物服务实现类
 * 提供作物的增删改查功能
 */
@Service
public class CropServiceImpl implements CropService {

    @Autowired
    private CropMapper cropMapper;

    //获取所有作物信息
    @Override
    public List<Crop> getAllCrops(Integer userId) {
        return cropMapper.findAllCrops(userId);
    }

    //根据作物ID获取作物信息
    @Override
    public Crop getCropById(Integer cropId) {
        return cropMapper.findCropById(cropId);
    }

    //添加作物
    @Override
    public void addCrop(CropAddDo crop) {
        cropMapper.insertCrop(crop);
    }

    //更新作物信息
    @Override
    public void updateCrop(CropPutDo crop) {
        cropMapper.updateCrop(crop);
    }

    //删除作物信息
    @Override
    public void deleteCrop(Integer cropId,Integer userId) {
        cropMapper.deleteCrop(cropId,userId);
    }
}
