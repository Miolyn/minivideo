package cn.tju.minivideo.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.tju.minivideo.entity.History;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {
    int deleteByPrimaryKey(Integer historyId);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer historyId);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

    List<History> findByItemTypeAndUserIdOrderByCreatedAt(@Param("itemType")Integer itemType,@Param("userId")String userId);


}