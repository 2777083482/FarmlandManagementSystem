package com.fms.mapper;
import com.fms.annotation.AutoFill;
import com.fms.enumeration.OperationType;
import com.fms.pojo.dto.FertilizerRecordAddDo;
import com.fms.pojo.dto.FertilizerRecordPutDo;
import com.fms.pojo.entity.FertilizerRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FertilizerRecordMapper {
    @Select("SELECT * FROM fertilizer_records WHERE plantings_id = #{plantingsId}")
    List<FertilizerRecord> findByPlantingsId(@Param("plantingsId") Integer plantingsId);

    @Select("SELECT * FROM fertilizer_records WHERE fertilizer_id = #{fertilizerId}")
    List<FertilizerRecord> findByFertilizerId(@Param("fertilizerId") Integer fertilizerId);

    @Insert("INSERT INTO fertilizer_records (plantings_id, fertilizer_date, fertilizer_type, amount, remarks, create_time, update_time) " +
            "VALUES (#{plantingsId}, #{fertilizerDate}, #{fertilizerType}, #{amount}, #{remarks}, #{createTime}, #{updateTime})")
    @AutoFill(OperationType.INSERT)
    void insertFertilizerRecord(FertilizerRecordAddDo fertilizerRecord);

    @AutoFill(OperationType.UPDATE)
    void updateFertilizerRecord(FertilizerRecordPutDo fertilizerRecord);

    @Delete("DELETE FROM fertilizer_records WHERE fertilizer_id = #{fertilizerId}")
    void deleteFertilizerRecord(@Param("fertilizerId") Integer fertilizerId);

}
