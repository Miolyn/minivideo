package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.Dynamic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DynamicMapper {
    int deleteByPrimaryKey(Integer dynamicId);

    int insert(Dynamic record);

    int insertSelective(Dynamic record);

    Dynamic selectByPrimaryKey(Integer dynamicId);

    int updateByPrimaryKeySelective(Dynamic record);

    int updateByPrimaryKey(Dynamic record);
}