package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.History;
import com.github.pagehelper.PageInfo;

public interface HistoryService{


    int deleteByPrimaryKey(Integer historyId);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer historyId);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

    int addHistory(String userId, Integer itemId, Integer itemType);

    PageInfo<History> getHistoriesByItemIdAndUserIdWithPaginatorOrderByTimeDesc(Integer itemType, String userId, Integer page, Integer pageSize);
}
