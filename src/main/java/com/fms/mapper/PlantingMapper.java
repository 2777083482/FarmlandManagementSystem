package com.fms.mapper;

import com.fms.annotation.AutoFill;
import com.fms.enumeration.OperationType;
import com.fms.pojo.dto.PlantingAddDo;
import com.fms.pojo.dto.PlantingPutDo;
import com.fms.pojo.entity.Planting;
import com.fms.pojo.vo.PlantingAllVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PlantingMapper {

    // 查询某农田的种植信息
    @Select("SELECT p.*,f.name,c.crop_name " +
            "FROM fields f " +
            "join (plantings p join crops c on p.crop_id = c.crop_id) " +
            "on f.field_id = p.field_id " +
            "where f.field_id = #{fieldId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "fieldName"),
            @Result(column = "crop_name", property = "cropName")
    }
    )
    List<PlantingAllVo> findByFieldId(@Param("fieldId") Integer fieldId);

    // 查询某作物的种植信息
    @Select("SELECT p.*,f.name,c.crop_name " +
            "FROM fields f " +
            "join (plantings p join crops c on p.crop_id = c.crop_id) " +
            "on f.field_id = p.field_id " +
            "WHERE c.crop_id = #{cropId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "fieldName"),
            @Result(column = "crop_name", property = "cropName")
    }
    )
    List<PlantingAllVo> findByCropId(@Param("cropId") Integer cropId);

    // 添加种植记录
    @Insert("INSERT INTO plantings (field_id, crop_id, growth_status, planting_date, harvest_date, create_time, update_time) " +
            "VALUES (#{fieldId}, #{cropId}, #{growthStatus}, #{plantingDate}, #{harvestDate}, #{createTime}, #{updateTime})")
    @AutoFill(OperationType.INSERT)
    void insertPlanting(PlantingAddDo planting);

    // 更新种植记录
    @AutoFill(OperationType.UPDATE)
    void updatePlanting(PlantingPutDo planting);

    // 删除种植记录
    @Delete("DELETE FROM plantings WHERE id = #{id}")
    void deletePlanting(@Param("id") Integer id);
    @Select("select * from plantings where field_id = #{fieldId} and crop_id = #{cropId}")
    Planting findByFieldIdAndCropId(@Param("fieldId")Integer fieldId,@Param("cropId")Integer cropId);
}
