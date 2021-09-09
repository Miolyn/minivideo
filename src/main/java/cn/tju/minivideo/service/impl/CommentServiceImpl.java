package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.constants.ProjectConstant;
import cn.tju.minivideo.core.exception.ServiceException;
import cn.tju.minivideo.dto.CommentDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.tju.minivideo.entity.Comment;
import cn.tju.minivideo.dao.CommentMapper;
import cn.tju.minivideo.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int deleteByPrimaryKey(Integer commentId) {
        return commentMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment selectByPrimaryKey(Integer commentId) {
        return commentMapper.selectByPrimaryKey(commentId);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean isCommentExistByCommentId(Integer commentId) {
        return commentMapper.selectByPrimaryKey(commentId) != null;
    }

    @Override
    public int addCommentLikeNumByCommentId(Integer commentId) {
        return commentMapper.updateLikeNumByCommentId(commentId);
    }


    @Override
    public List<CommentDto> getCommentsByItemIdAndItemTypeWithPaginator(Integer itemId, Integer itemType, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CommentDto> commentDtos = new ArrayList<>();
        PageInfo<Comment> commentPageInfo = new PageInfo<>(commentMapper.findByToIdAndCommentType(itemId, itemType));
        List<Comment> comments = commentPageInfo.getList();
        for (Comment comment : comments) {
            CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
            if (!comment.getCommentType().equals(Constants.CommentConst.CommentOnComment)){
                PageHelper.startPage(1, ProjectConstant.SmallPageSize);
                List<Comment> commentReplays = commentMapper.findByToIdAndCommentType(comment.getCommentId(), Constants.CommentConst.CommentOnComment);
                List<CommentDto> commentDtoReplays = new ArrayList<>();
                commentReplays.forEach(comment1 -> commentDtoReplays.add(modelMapper.map(comment1, CommentDto.class)));
                commentDto.setReplays(commentDtoReplays);
            }
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    @Override
    public void lockCommentByCommentId(Integer commentId) {
        Comment comment = commentMapper.findByCommentIdForUpdate(commentId);
        if (comment == null){
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
    }

    @Override
    public Comment getCommentByCommentId(Integer commentId) {
        Comment comment = commentMapper.findByCommentId(commentId);
        if(comment == null){
            throw new ServiceException(MsgEnums.ITEM_NOT_EXIST);
        }
        return comment;
    }


}
