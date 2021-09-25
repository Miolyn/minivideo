package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.User;
import com.github.pagehelper.PageInfo;

public interface UserService {


    public interface FollowUserAction {
        Integer followUser = 1;
        Integer unFollowUser = 2;
    }

    interface SortMethod {
        Integer SortByTimeDesc = 1;

    }

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    boolean isExistByUsername(String username);

    User checkUsernameAndPassword(String username, String password);

    int updateUserFollowNumByAction(String userId, Integer action);

    int updateUserFansNumByAction(String userId, Integer action);

    User getUserByUserIdWithRedis(String userId);


    User findByUserId(String userId);

    void lockUserByUserId(String userId);

    int updateUserLikeNum(String userId);

    PageInfo<User> searchUserByKeyOnUsernameWithPaginator(String key, Integer sortMethod, Integer page, Integer pageSize);
}








