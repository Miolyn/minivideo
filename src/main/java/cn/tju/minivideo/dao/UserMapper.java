package cn.tju.minivideo.dao;
import java.util.List;

import cn.tju.minivideo.entity.User;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUsername(@Param("username") String username);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findByUserId(@Param("userId")String userId);

    User findByUserIdForUpdate(@Param("userId")String userId);

    int updateLikeNumByUserId(@Param("userId")String userId);


}