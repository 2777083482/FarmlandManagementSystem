package com.fms.service.impl;
import com.fms.mapper.IrrigationRecordMapper;
import com.fms.pojo.dto.IrrigationRecordAddDo;
import com.fms.pojo.dto.IrrigationRecordPutDo;
import com.fms.pojo.entity.IrrigationRecord;
import com.fms.service.IrrigationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IrrigationRecordServiceImpl implements IrrigationRecordService {

    @Autowired
    private IrrigationRecordMapper irrigationRecordMapper;

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
}

