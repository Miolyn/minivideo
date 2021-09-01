package cn.tju.minivideo.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import cn.tju.minivideo.entity.Relation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelationMapper {
    int deleteByPrimaryKey(Integer relationId);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(Integer relationId);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);

    Relation findByFromIdAndToIdAndRelationType(@Param("fromId") String fromId, @Param("toId") String toId, @Param("relationType") Integer relationType);

    List<Relation> findByFromIdAndRelationType(@Param("fromId") String fromId, @Param("relationType") Integer relationType);

    int deleteByFromIdAndToIdAndRelationType(@Param("fromId") String fromId, @Param("toId") String toId, @Param("relationType") Integer relationType);

}