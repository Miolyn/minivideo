package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Dynamic;
import com.github.pagehelper.PageInfo;

public interface DynamicService {


    int deleteByPrimaryKey(Integer dynamicId);

    int insert(Dynamic record);

    int insertSelective(Dynamic record);

    Dynamic selectByPrimaryKey(Integer dynamicId);

    int updateByPrimaryKeySelective(Dynamic record);

    int updateByPrimaryKey(Dynamic record);

    int createVideoAutoDynamic(String userId, Integer videoId);

    PageInfo<Dynamic> getDynamicsWhereUserIsFollowedWithPaginator(String userId, Integer page, Integer pageSize);

    boolean isExistDynamicByDynamicId(Integer dynamicId);

    void lockDynamicByDynamicId(Integer dynamicId);

    int addDynamicLikeNumByDynamicId(Integer dynamicId);
}


