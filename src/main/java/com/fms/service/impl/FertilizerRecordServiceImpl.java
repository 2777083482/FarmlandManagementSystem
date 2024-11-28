package com.fms.service.impl;
import com.fms.mapper.FertilizerRecordMapper;
import com.fms.pojo.entity.FertilizerRecord;
import com.fms.service.FertilizerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FertilizerRecordServiceImpl implements FertilizerRecordService {

    @Autowired
    private FertilizerRecordMapper fertilizerRecordMapper;

    @Override
    public List<FertilizerRecord> getFertilizerRecordsByPlantingsId(Integer plantingsId) {
        return fertilizerRecordMapper.findByPlantingsId(plantingsId);
    }

    @Override
    public void addFertilizerRecord(FertilizerRecord fertilizerRecord) {
        fertilizerRecordMapper.insertFertilizerRecord(fertilizerRecord);
    }

    @Override
    public void updateFertilizerRecord(FertilizerRecord fertilizerRecord) {
        fertilizerRecordMapper.updateFertilizerRecord(fertilizerRecord);
    }

    @Override
    public void deleteFertilizerRecord(Integer fertilizerId) {
        fertilizerRecordMapper.deleteFertilizerRecord(fertilizerId);
    }
}
