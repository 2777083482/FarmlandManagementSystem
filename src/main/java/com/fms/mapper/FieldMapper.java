package com.fms.mapper;

import com.fms.annotation.AutoFill;
import com.fms.enumeration.OperationType;
import com.fms.pojo.dto.FieldAddDo;
import com.fms.pojo.dto.FieldPutDo;
import com.fms.pojo.entity.Field;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FieldMapper {
    // 根据用户ID获取农田列表
    @Select("SELECT * FROM fields WHERE user_id = #{userId}")
    List<Field> findFieldsByUserId(@Param("userId") int userId);

    // 添加农田
    @Insert("INSERT INTO fields (name, area, location, soil_type, crop_type, planting_date, harvest_date, user_id,create_time,update_time) " +
            "VALUES (#{name}, #{area}, #{location}, #{soilType}, #{cropType}, #{plantingDate}, #{harvestDate}, #{userId}, #{createTime},#{updateTime})")
    @AutoFill(OperationType.INSERT)
    void insertField(FieldAddDo fieldAddDo);

    // 更新农田
    @AutoFill(OperationType.UPDATE)
    void updateField(FieldPutDo fieldPutDo);

    // 删除农田
    @Delete("DELETE FROM fields WHERE field_id = #{fieldId} AND user_id = #{userId}")
    void deleteField(@Param("fieldId") int fieldId, @Param("userId") int userId);
}

