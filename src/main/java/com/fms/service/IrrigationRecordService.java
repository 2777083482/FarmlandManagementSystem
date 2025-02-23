package com.fms.service;
import com.fms.pojo.dto.IrrigationRecordAddDo;
import com.fms.pojo.dto.IrrigationRecordPutDo;
import com.fms.pojo.entity.IrrigationRecord;

import java.util.List;

public interface IrrigationRecordService {
    List<IrrigationRecord> getIrrigationRecordsByPlantingsId(Integer plantingsId);
    List<IrrigationRecord> getIrrigationRecordsByIrrigationId(Integer irrigationId);
    void addIrrigationRecord(IrrigationRecordAddDo irrigationRecord);
    void updateIrrigationRecord(IrrigationRecordPutDo irrigationRecord);
    void deleteIrrigationRecord(Integer irrigationId);
    List<IrrigationRecord> getTodayIrrigationRecordsByUserId(Integer userId);
}

