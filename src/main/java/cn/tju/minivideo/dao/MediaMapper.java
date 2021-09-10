package cn.tju.minivideo.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import cn.tju.minivideo.entity.Media;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MediaMapper extends BaseMapper<Media> {
    int deleteByPrimaryKey(Integer mediaId);

    int insert(Media record);

    int insertSelective(Media record);

    Media selectByPrimaryKey(Integer mediaId);

    int updateByPrimaryKeySelective(Media record);

    int updateByPrimaryKey(Media record);

    Media findByMediaUrl(@Param("mediaUrl") String mediaUrl);

    Media findByMediaUrlAndMediaType(@Param("mediaUrl")String mediaUrl,@Param("mediaType")Integer mediaType);



}