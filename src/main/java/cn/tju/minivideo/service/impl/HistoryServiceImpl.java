package cn.tju.minivideo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.entity.History;
import cn.tju.minivideo.dao.HistoryMapper;
import cn.tju.minivideo.service.HistoryService;
@Service
public class HistoryServiceImpl implements HistoryService{

    @Resource
    private HistoryMapper historyMapper;

    @Override
    public int deleteByPrimaryKey(Integer historyId) {
        return historyMapper.deleteByPrimaryKey(historyId);
    }

    @Override
    public int insert(History record) {
        return historyMapper.insert(record);
    }

    @Override
    public int insertSelective(History record) {
        return historyMapper.insertSelective(record);
    }

    @Override
    public History selectByPrimaryKey(Integer historyId) {
        return historyMapper.selectByPrimaryKey(historyId);
    }

    @Override
    public int updateByPrimaryKeySelective(History record) {
        return historyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(History record) {
        return historyMapper.updateByPrimaryKey(record);
    }

    @Override
    public int addHistory(String userId, Integer itemId, Integer itemType) {
        History history = new History(userId, itemId, itemType);
        return insertSelective(history);
    }

    @Override
    public PageInfo<History> getHistoriesByItemIdAndUserIdWithPaginatorOrderByTimeDesc(Integer itemType, String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(historyMapper.findByItemTypeAndUserIdOrderByCreatedAt(itemType, userId));
    }

}
