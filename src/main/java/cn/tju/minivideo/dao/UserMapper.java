package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("userId") String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("id") Integer id, @Param("userId") String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int deleteByPrimaryKey(String userId);

    User selectByPrimaryKey(String userId);

    User findByUsername(@Param("username") String username);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findByUserId(@Param("userId") String userId);

    User findByUserIdForUpdate(@Param("userId") String userId);

    int updateLikeNumByUserId(@Param("userId") String userId);

    List<User> searchKeyOnUsernameOrderByCreatedAt(@Param("key") String key);
}