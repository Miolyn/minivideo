package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.DynamicMapper;
import cn.tju.minivideo.entity.Dynamic;
import cn.tju.minivideo.service.DynamicService;

@Service
public class DynamicServiceImpl implements DynamicService {

    @Resource
    private DynamicMapper dynamicMapper;

    @Override
    public int deleteByPrimaryKey(Integer dynamicId) {
        return dynamicMapper.deleteByPrimaryKey(dynamicId);
    }

    @Override
    public int insert(Dynamic record) {
        return dynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(Dynamic record) {
        return dynamicMapper.insertSelective(record);
    }

    @Override
    public Dynamic selectByPrimaryKey(Integer dynamicId) {
        return dynamicMapper.selectByPrimaryKey(dynamicId);
    }

    @Override
    public int updateByPrimaryKeySelective(Dynamic record) {
        return dynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Dynamic record) {
        return dynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public int createVideoAutoDynamic(String userId, Integer videoId) {
        Dynamic dynamic = new Dynamic();
        dynamic.setUserId(userId);
        dynamic.setContent(String.valueOf(videoId));
        dynamic.setDynamicType(Constants.DynamicConst.AutoDynamicType);
        return dynamicMapper.insertSelective(dynamic);
    }

    @Override
    public PageInfo<Dynamic> getDynamicsWhereUserIsFollowedWithPaginator(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(dynamicMapper.findDynamicByFollowUserIdOrderByCreatedAt(userId));
    }

    @Override
    public boolean isExistDynamicByDynamicId(Integer dynamicId) {
        return dynamicMapper.findByDynamicId(dynamicId) != null;
    }

    @Override
    public void lockDynamicByDynamicId(Integer dynamicId) {
        dynamicMapper.findByDynamicIdForUpdate(dynamicId);
    }

    @Override
    public int addDynamicLikeNumByDynamicId(Integer dynamicId) {
        return dynamicMapper.updateLikeNumByDynamicId(dynamicId);
    }

    @Override
    public int addDynamicCommentNumByDynamicId(Integer dynamicId) {
        return dynamicMapper.updateCommentNumByDynamicId(dynamicId);
    }

    @Override
    public String getUserIdOfDynamicByDynamicId(Integer dynamicId) {
        String userId = dynamicMapper.findUserIdByDynamicId(dynamicId);
        if(userId == null || userId.equals("")){
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
        return userId;
    }


}


