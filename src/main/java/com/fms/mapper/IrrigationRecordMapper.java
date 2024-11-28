package com.fms.mapper;
import com.fms.annotation.AutoFill;
import com.fms.enumeration.OperationType;
import com.fms.pojo.dto.IrrigationRecordAddDo;
import com.fms.pojo.dto.IrrigationRecordPutDo;
import com.fms.pojo.entity.IrrigationRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IrrigationRecordMapper {
    @Select("SELECT * FROM irrigation_records WHERE plantings_id = #{plantingsId}")
    List<IrrigationRecord> findByPlantingsId(@Param("plantingsId") Integer plantingsId);

    @Select("SELECT * FROM irrigation_records WHERE irrigation_id = #{irrigationId}")
    List<IrrigationRecord> findByIrrigationId(@Param("irrigationId") Integer irrigationId);

    @Insert("INSERT INTO irrigation_records (plantings_id, irrigation_date, amount, remarks, create_time, update_time) " +
            "VALUES (#{plantingsId}, #{irrigationDate}, #{amount}, #{remarks}, #{createTime}, #{updateTime})")
    @AutoFill(OperationType.INSERT)
    void insertIrrigationRecord(IrrigationRecordAddDo irrigationRecord);

    @AutoFill(OperationType.UPDATE)
    void updateIrrigationRecord(IrrigationRecordPutDo irrigationRecord);

    @Delete("DELETE FROM irrigation_records WHERE irrigation_id = #{irrigationId}")
    void deleteIrrigationRecord(@Param("irrigationId") Integer irrigationId);

}

