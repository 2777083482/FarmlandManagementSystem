package com.fms.mapper;

import com.fms.pojo.entity.Planting;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PlantingMapper {
    // 查询某农田的种植信息
    @Select("SELECT * FROM plantings WHERE field_id = #{fieldId}")
    List<Planting> findByFieldId(@Param("fieldId") Integer fieldId);

    // 查询某作物的种植信息
    @Select("SELECT * FROM plantings WHERE crop_id = #{cropId}")
    List<Planting> findByCropId(@Param("cropId") Integer cropId);

    // 添加种植记录
    @Insert("INSERT INTO plantings (field_id, crop_id, growth_status, planting_date, harvest_date, create_time, update_time) " +
            "VALUES (#{fieldId}, #{cropId}, #{growthStatus}, #{plantingDate}, #{harvestDate}, NOW(), NOW())")
    void insertPlanting(Planting planting);

    // 更新种植记录
    @Update("UPDATE plantings SET growth_status = #{growthStatus}, planting_date = #{plantingDate}, " +
            "harvest_date = #{harvestDate}, update_time = NOW() WHERE id = #{id}")
    void updatePlanting(Planting planting);

    // 删除种植记录
    @Delete("DELETE FROM plantings WHERE id = #{id}")
    void deletePlanting(@Param("id") Integer id);
}
