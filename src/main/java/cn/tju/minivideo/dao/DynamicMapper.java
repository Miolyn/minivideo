package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.Dynamic;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface DynamicMapper {
    int deleteByPrimaryKey(Integer dynamicId);

    int insert(Dynamic record);

    int insertSelective(Dynamic record);

    Dynamic selectByPrimaryKey(Integer dynamicId);

    int updateByPrimaryKeySelective(Dynamic record);

    int updateByPrimaryKey(Dynamic record);

    List<Dynamic> findDynamicByFollowUserIdOrderByCreatedAt(@Param("userId") String userId);

    Dynamic findByDynamicId(@Param("dynamicId")Integer dynamicId);

    Dynamic findByDynamicIdForUpdate(@Param("dynamicId")Integer dynamicId);

	int updateLikeNumByDynamicId(@Param("dynamicId")Integer dynamicId);


}