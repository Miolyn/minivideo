package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.entity.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.RelationMapper;
import cn.tju.minivideo.entity.Relation;
import cn.tju.minivideo.service.RelationService;
@Service
public class RelationServiceImpl implements RelationService{

    @Resource
    private RelationMapper relationMapper;

    @Override
    public int deleteByPrimaryKey(Integer relationId) {
        return relationMapper.deleteByPrimaryKey(relationId);
    }

    @Override
    public int insert(Relation record) {
        return relationMapper.insert(record);
    }

    @Override
    public int insertSelective(Relation record) {
        return relationMapper.insertSelective(record);
    }

    @Override
    public Relation selectByPrimaryKey(Integer relationId) {
        return relationMapper.selectByPrimaryKey(relationId);
    }

    @Override
    public int updateByPrimaryKeySelective(Relation record) {
        return relationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Relation record) {
        return relationMapper.updateByPrimaryKey(record);
    }


    @Override
    public boolean isExistRelation(String fromId, String toId, Integer relationType){
        return relationMapper.findByFromIdAndToIdAndRelationType(fromId, toId, relationType) != null;
    }

    @Override
    public PageInfo<Relation> findRelationsByFromIdAndRelationTypeWithPaginator(String fromId, Integer relationType, Integer page, Integer pageSize){
        PageHelper.startPage(page, pageSize);
        PageInfo<Relation> pageInfo = new PageInfo<>(relationMapper.findByFromIdAndRelationType(fromId, relationType));
        return pageInfo;
    }

    @Override
    public int deleteRelationByFromIdAndToIdAndRelationType(String fromId, String toId, Integer relationType){
        return relationMapper.deleteByFromIdAndToIdAndRelationType(fromId, toId, relationType);
    }
}
