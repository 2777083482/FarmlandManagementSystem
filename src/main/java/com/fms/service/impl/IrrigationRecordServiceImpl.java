package com.fms.service.impl;
import com.fms.mapper.IrrigationRecordMapper;
import com.fms.pojo.dto.IrrigationRecordAddDo;
import com.fms.pojo.dto.IrrigationRecordPutDo;
import com.fms.pojo.entity.FertilizerRecord;
import com.fms.pojo.entity.IrrigationRecord;
import com.fms.pojo.vo.PlantingAllVo;
import com.fms.service.IrrigationRecordService;
import com.fms.service.PlantingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class IrrigationRecordServiceImpl implements IrrigationRecordService {

    @Autowired
    private IrrigationRecordMapper irrigationRecordMapper;

    @Autowired
    private PlantingService plantingService;

    @Override
    public List<IrrigationRecord> getIrrigationRecordsByPlantingsId(Integer plantingsId) {
        return irrigationRecordMapper.findByPlantingsId(plantingsId);
    }

    @Override
    public List<IrrigationRecord> getIrrigationRecordsByIrrigationId(Integer irrigationId) {
        return irrigationRecordMapper.findByIrrigationId(irrigationId);
    }

    @Override
    public void addIrrigationRecord(IrrigationRecordAddDo irrigationRecord) {
        irrigationRecordMapper.insertIrrigationRecord(irrigationRecord);
    }

    @Override
    public void updateIrrigationRecord(IrrigationRecordPutDo irrigationRecord) {
        irrigationRecordMapper.updateIrrigationRecord(irrigationRecord);
    }

    @Override
    public void deleteIrrigationRecord(Integer irrigationId) {
        irrigationRecordMapper.deleteIrrigationRecord(irrigationId);
    }

    public boolean localDateIsEqual(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isEqual(secondDate);
    }

    @Override
    public List<IrrigationRecord> getTodayIrrigationRecordsByUserId(Integer userId) {
        List<PlantingAllVo> plantings = plantingService.getPlantingsByUserId(userId);
        List<IrrigationRecord> irrigationRecords = new ArrayList<>();
        for (PlantingAllVo planting : plantings) {
            List<IrrigationRecord> irrigation = getIrrigationRecordsByPlantingsId(planting.getId());
            irrigationRecords.addAll(irrigation);
        }

        List<IrrigationRecord> returnIrrigationRecords = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        OUT:for (IrrigationRecord irrigationRecord : irrigationRecords) {
            if (irrigationRecord.getIrrigationDate() == null) {
                continue OUT;
            }
            boolean b = localDateIsEqual(localDate, irrigationRecord.getIrrigationDate());
            if (b) {
                for (IrrigationRecord returnIrrigationRecord : returnIrrigationRecords) {
                    if (returnIrrigationRecord.getPlantingsId().equals(irrigationRecord.getPlantingsId())) {
                        continue OUT;
                    }
                }
                returnIrrigationRecords.add(irrigationRecord);
            }
        }

        return returnIrrigationRecords;
    }
}

