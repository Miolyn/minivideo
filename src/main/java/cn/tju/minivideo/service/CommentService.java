package cn.tju.minivideo.service;

import cn.tju.minivideo.dto.CommentDto;
import cn.tju.minivideo.entity.Comment;

import java.util.List;

public interface CommentService{


    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    boolean isCommentExistByCommentId(Integer commentId);

    int addCommentLikeNumByCommentId(Integer commentId);

    List<CommentDto> getCommentsByItemIdAndItemTypeWithPaginator(Integer itemId, Integer itemType, Integer page, Integer pageSize);

    void lockCommentByCommentId(Integer commentId);
}
