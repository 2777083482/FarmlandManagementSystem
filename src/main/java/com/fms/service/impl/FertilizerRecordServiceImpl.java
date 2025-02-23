package com.fms.service.impl;
import com.fms.mapper.FertilizerRecordMapper;
import com.fms.pojo.dto.FertilizerRecordAddDo;
import com.fms.pojo.dto.FertilizerRecordPutDo;
import com.fms.pojo.entity.FertilizerRecord;
import com.fms.pojo.vo.PlantingAllVo;
import com.fms.service.FertilizerRecordService;
import com.fms.service.PlantingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FertilizerRecordServiceImpl implements FertilizerRecordService {

    @Autowired
    private FertilizerRecordMapper fertilizerRecordMapper;

    @Autowired
    private PlantingService plantingService;

    @Override
    public List<FertilizerRecord> getFertilizerRecordsByPlantingsId(Integer plantingsId) {
        return fertilizerRecordMapper.findByPlantingsId(plantingsId);
    }

    @Override
    public void addFertilizerRecord(FertilizerRecordAddDo fertilizerRecord) {
        fertilizerRecordMapper.insertFertilizerRecord(fertilizerRecord);
    }

    @Override
    public void updateFertilizerRecord(FertilizerRecordPutDo fertilizerRecord) {
        fertilizerRecordMapper.updateFertilizerRecord(fertilizerRecord);
    }

    @Override
    public void deleteFertilizerRecord(Integer fertilizerId) {
        fertilizerRecordMapper.deleteFertilizerRecord(fertilizerId);
    }

    @Override
    public List<FertilizerRecord> getFertilizerRecordsByFertilizerId(Integer fertilizerId) {
        return fertilizerRecordMapper.findByFertilizerId(fertilizerId);
    }

    public boolean localDateIsEqual(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isEqual(secondDate);
    }

    @Override
    public List<FertilizerRecord> getTodayFertilizerRecordsByUserId(Integer userId) {
        List<PlantingAllVo> plantings = plantingService.getPlantingsByUserId(userId);
        List<FertilizerRecord> fertilizerRecords = new ArrayList<>();
        for (PlantingAllVo planting : plantings) {
            List<FertilizerRecord> fertilizers = getFertilizerRecordsByPlantingsId(planting.getId());
            fertilizerRecords.addAll(fertilizers);
        }

        List<FertilizerRecord> returnFertilizerRecords = new ArrayList<>();
        LocalDate localDate = LocalDate.now();

        OUT:for (FertilizerRecord fertilizerRecord : fertilizerRecords) {
            if (fertilizerRecord.getFertilizerDate() == null) {
                continue OUT;
            }
            boolean b = localDateIsEqual(localDate, fertilizerRecord.getFertilizerDate());
            if (b) {
                for (FertilizerRecord returnFertilizerRecord : returnFertilizerRecords) {
                    if (returnFertilizerRecord.getPlantingsId().equals(fertilizerRecord.getPlantingsId())) {
                        continue OUT;
                    }
                }
                returnFertilizerRecords.add(fertilizerRecord);
            }
        }

        return returnFertilizerRecords;
    }
}
