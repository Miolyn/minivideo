package cn.tju.minivideo.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.tju.minivideo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> findByToIdAndCommentType(@Param("toId")Integer toId,@Param("commentType")Integer commentType);

    int updateLikeNumByCommentId(@Param("commentId")Integer commentId);

    Comment findByCommentIdForUpdate(@Param("commentId")Integer commentId);

    Comment findByCommentId(@Param("commentId")Integer commentId);


}