package cn.tju.minivideo.service;

import cn.tju.minivideo.dto.MsgDto;
import cn.tju.minivideo.entity.Message;
import com.github.pagehelper.PageInfo;

public interface MessageService {


    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);


//    void addSystemNotifyMessage(Integer messageType, Integer itemId, Integer itemType, String content);
    void addSystemNotifyMessage(String toId, Integer messageType, MsgDto msgDto);

    MsgDto getMsgDtoByObject(Object o, Integer itemType, Integer messageType);

    PageInfo<Message> getMessagesByToIdAndMessageTypeWithPaginator(String userId, Integer messageType, Integer page, Integer pageSize);
}


