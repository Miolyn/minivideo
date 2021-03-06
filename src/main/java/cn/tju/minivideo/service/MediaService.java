package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Media;
public interface MediaService{


    int deleteByPrimaryKey(Integer mediaId);

    int insert(Media record);

    int insertSelective(Media record);

    Media selectByPrimaryKey(Integer mediaId);

    int updateByPrimaryKeySelective(Media record);

    int updateByPrimaryKey(Media record);

    boolean isExistByMediaUrlAndTrueFile(String mediaUrl);

    boolean isExistByMediaUrlAndMediaTypeAndTrueFile(String mediaUrl, Integer mediaType);
}
