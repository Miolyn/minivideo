package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Relation;
import com.github.pagehelper.PageInfo;

public interface RelationService{


    int deleteByPrimaryKey(Integer relationId);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(Integer relationId);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);

    boolean isExistRelation(String fromId, String toId, Integer relationType);

    PageInfo<Relation> findRelationsByFromIdAndRelationTypeWithPaginator(String fromId, Integer relationType, Integer page, Integer pageSize);

    int deleteRelationByFromIdAndToIdAndRelationType(String fromId, String toId, Integer relationType);
}
