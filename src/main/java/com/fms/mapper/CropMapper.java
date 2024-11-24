package com.fms.mapper;

import com.fms.annotation.AutoFill;
import com.fms.enumeration.OperationType;
import com.fms.pojo.dto.CropAddDo;
import com.fms.pojo.dto.CropPutDo;
import com.fms.pojo.entity.Crop;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CropMapper {
    // 查询所有作物
    @Select("SELECT * FROM crops where user_id = #{userId}")
    List<Crop> findAllCrops(Integer userId);

    // 根据ID查询作物
    @Select("SELECT * FROM crops WHERE crop_id = #{cropId}")
    Crop findCropById(@Param("cropId") Integer cropId);

    // 添加作物
    @Insert("INSERT INTO crops (crop_name,user_id, create_time, update_time) VALUES (#{cropName}, #{userId},#{createTime},#{updateTime})")
    @AutoFill(OperationType.INSERT)
    void insertCrop(CropAddDo crop);

    // 更新作物
    //@Update("UPDATE crops SET crop_name = #{cropName}, update_time = #{updateTime} WHERE crop_id = #{cropId}")
    @AutoFill(OperationType.UPDATE)
    void updateCrop(CropPutDo crop);

    // 删除作物
    @Delete("DELETE FROM crops WHERE crop_id = #{cropId} and user_id = #{userId}")
    void deleteCrop(@Param("cropId") Integer cropId,@Param("userId") Integer userId);
}
