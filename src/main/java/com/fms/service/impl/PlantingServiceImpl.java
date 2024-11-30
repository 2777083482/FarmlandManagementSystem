package com.fms.service.impl;

import com.fms.mapper.FertilizerRecordMapper;
import com.fms.mapper.IrrigationRecordMapper;
import com.fms.mapper.PlantingMapper;
import com.fms.pojo.dto.PlantingAddDo;
import com.fms.pojo.dto.PlantingPutDo;
import com.fms.pojo.entity.FertilizerRecord;
import com.fms.pojo.entity.IrrigationRecord;
import com.fms.pojo.entity.Planting;
import com.fms.pojo.vo.PlantingAllVo;
import com.fms.service.PlantingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlantingServiceImpl implements PlantingService {

    @Autowired
    private PlantingMapper plantingMapper;

    @Autowired
    private IrrigationRecordMapper irrigationRecordMapper;

    @Autowired
    private FertilizerRecordMapper fertilizerRecordMapper;

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
    @Transactional
    public void deletePlanting(Integer id) {
        List<IrrigationRecord> irrigationRecords = irrigationRecordMapper.findByPlantingsId(id);
        if (irrigationRecords != null) {
            for (IrrigationRecord irrigationRecord : irrigationRecords) {
                irrigationRecordMapper.deleteIrrigationRecord(irrigationRecord.getIrrigationId());
            }
        }
        List<FertilizerRecord> fertilizerRecords = fertilizerRecordMapper.findByPlantingsId(id);
        if (fertilizerRecords != null) {
            for (FertilizerRecord fertilizerRecord : fertilizerRecords) {
                fertilizerRecordMapper.deleteFertilizerRecord(fertilizerRecord.getFertilizerId());
            }
        }
        plantingMapper.deletePlanting(id);
    }
}
