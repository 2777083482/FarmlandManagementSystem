package com.fms.service;
import com.fms.pojo.dto.FertilizerRecordAddDo;
import com.fms.pojo.dto.FertilizerRecordPutDo;
import com.fms.pojo.entity.FertilizerRecord;

import java.util.List;

public interface FertilizerRecordService {
    List<FertilizerRecord> getFertilizerRecordsByPlantingsId(Integer plantingsId);
    void addFertilizerRecord(FertilizerRecordAddDo fertilizerRecord);
    void updateFertilizerRecord(FertilizerRecordPutDo fertilizerRecord);
    void deleteFertilizerRecord(Integer fertilizerId);

    List<FertilizerRecord> getFertilizerRecordsByFertilizerId(Integer fertilizerId);
}

