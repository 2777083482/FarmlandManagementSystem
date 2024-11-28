package com.fms.service;
import com.fms.pojo.entity.FertilizerRecord;

import java.util.List;

public interface FertilizerRecordService {
    List<FertilizerRecord> getFertilizerRecordsByPlantingsId(Integer plantingsId);
    void addFertilizerRecord(FertilizerRecord fertilizerRecord);
    void updateFertilizerRecord(FertilizerRecord fertilizerRecord);
    void deleteFertilizerRecord(Integer fertilizerId);
}

