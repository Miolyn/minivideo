package cn.tju.minivideo.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.tju.minivideo.entity.Label;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LabelMapper {
    int deleteByPrimaryKey(Integer labelId);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Integer labelId);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

    Label findByLabelNameAndLabelType(@Param("labelName")String labelName,@Param("labelType")Integer labelType);

    Label findByLabelId(@Param("labelId")Integer labelId);

    List<Label> findByLabelType(@Param("labelType")Integer labelType);


}