package com.fms.mapper;
import com.fms.pojo.entity.FertilizerRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FertilizerRecordMapper {
    @Select("SELECT * FROM fertilizer_records WHERE plantings_id = #{plantingsId}")
    List<FertilizerRecord> findByPlantingsId(@Param("plantingsId") Integer plantingsId);

    @Insert("INSERT INTO fertilizer_records (plantings_id, fertilizer_date, fertilizer_type, amount, remarks, create_time, update_time) " +
            "VALUES (#{plantingsId}, #{fertilizerDate}, #{fertilizerType}, #{amount}, #{remarks}, NOW(), NOW())")
    void insertFertilizerRecord(FertilizerRecord fertilizerRecord);

    @Update("UPDATE fertilizer_records SET fertilizer_date = #{fertilizerDate}, fertilizer_type = #{fertilizerType}, " +
            "amount = #{amount}, remarks = #{remarks}, update_time = NOW() WHERE fertilizer_id = #{fertilizerId}")
    void updateFertilizerRecord(FertilizerRecord fertilizerRecord);

    @Delete("DELETE FROM fertilizer_records WHERE fertilizer_id = #{fertilizerId}")
    void deleteFertilizerRecord(@Param("fertilizerId") Integer fertilizerId);
}
