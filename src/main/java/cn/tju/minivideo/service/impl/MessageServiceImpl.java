package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.Constants;
import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import cn.tju.minivideo.core.util.JsonUtil;
import cn.tju.minivideo.dto.MsgDto;
import cn.tju.minivideo.entity.Comment;
import cn.tju.minivideo.entity.LikeMap;
import cn.tju.minivideo.entity.Video;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.entity.Message;
import cn.tju.minivideo.dao.MessageMapper;
import cn.tju.minivideo.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public int deleteByPrimaryKey(Integer messageId) {
        return messageMapper.deleteByPrimaryKey(messageId);
    }

    @Override
    public int insert(Message record) {
        return messageMapper.insert(record);
    }

    @Override
    public int insertSelective(Message record) {
        return messageMapper.insertSelective(record);
    }

    @Override
    public Message selectByPrimaryKey(Integer messageId) {
        return messageMapper.selectByPrimaryKey(messageId);
    }

    @Override
    public int updateByPrimaryKeySelective(Message record) {
        return messageMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int updateByPrimaryKey(Message record) {
        return messageMapper.updateByPrimaryKey(record);
    }

    @Override
    public void addSystemNotifyMessage(String toId, Integer messageType, MsgDto msgDto) {
        String content = JsonUtil.Object2String(msgDto);
        Message message = new Message(Constants.MessageConst.FromSystemId, Constants.MessageConst.FromTypeSystem, toId, messageType, content);
        messageMapper.insertSelective(message);
    }

    @Override
    public MsgDto getMsgDtoByObject(Object o, Integer itemType, Integer messageType) {
        if(messageType.equals(Constants.MessageConst.MessageLikeNotify)){
            LikeMap likeMap = (LikeMap)o;
            return new MsgDto(likeMap.getFromId(), likeMap.getToId(), itemType, "");
        } else if (messageType.equals(Constants.MessageConst.MessageTypeCommentNotify)){
            Comment comment = (Comment) o;
            return new MsgDto(comment.getFromId(), comment.getToId(), itemType, comment.getContent());
        } else{
            throw new ServiceException(MsgEnums.ACTION_NOT_FOUND);
        }
    }

    @Override
    public PageInfo<Message> getMessagesByToIdAndMessageTypeWithPaginator(String userId, Integer messageType, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(messageMapper.findByToIdAndMessageType(userId, messageType));
    }

}


