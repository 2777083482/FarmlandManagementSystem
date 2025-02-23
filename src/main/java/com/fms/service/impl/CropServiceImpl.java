package com.fms.service.impl;

import com.fms.exception.CommonException;
import com.fms.mapper.CropMapper;
import com.fms.mapper.PlantingMapper;
import com.fms.pojo.dto.CropAddDo;
import com.fms.pojo.dto.CropPutDo;
import com.fms.pojo.entity.Crop;
import com.fms.pojo.vo.PlantingAllVo;
import com.fms.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作物服务实现类
 * 提供作物的增删改查功能
 */
@Service
public class CropServiceImpl implements CropService {

    @Autowired
    private CropMapper cropMapper;

    @Autowired
    private PlantingMapper plantingMapper;

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
        if (crop.getCropName() == null || crop.getCropName().trim().equals("")){
            throw new CommonException("作物名称不许为空");
        }
        cropMapper.updateCrop(crop);
    }

    //删除作物信息
    @Override
    @Transactional
    public void deleteCrop(Integer cropId,Integer userId) {
        List<PlantingAllVo> plantings = plantingMapper.findByCropId(cropId);
        if (plantings != null) {
            for (PlantingAllVo planting : plantings) {
                plantingMapper.deletePlanting(planting.getId());
            }
        }
        cropMapper.deleteCrop(cropId,userId);
    }
}
