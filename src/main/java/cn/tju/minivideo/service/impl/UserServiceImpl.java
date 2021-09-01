package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.dao.UserMapper;
import cn.tju.minivideo.entity.User;
import cn.tju.minivideo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean isExistByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user != null;
    }

    @Override
    public User checkUsernameAndPassword(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new ServiceException(MsgEnums.USERNAME_OR_PASSWORD_ERROR);
        }
        return user;
    }


    @Override
    public int updateUserFollowNumByAction(String userId, Integer action){
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null){
            throw new ServiceException(MsgEnums.USER_NOT_EXIST);
        }
        if (action.equals(FollowUserAction.followUser)){
            user.setFollowNum(user.getFollowNum() + 1);
        } else if (action.equals(FollowUserAction.unFollowUser)){
            if (user.getFollowNum() == 0) return 1;
            user.setFollowNum(user.getFollowNum() - 1);
        } else {
            throw new ServiceException(MsgEnums.ACTION_NOT_FOUND);
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }
}






