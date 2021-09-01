package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.tju.minivideo.dao.MediaMapper;
import cn.tju.minivideo.entity.Media;
import cn.tju.minivideo.service.MediaService;

@Service
@Slf4j
public class MediaServiceImpl implements MediaService {

    @Resource
    private MediaMapper mediaMapper;

    @Override
    public int deleteByPrimaryKey(Integer mediaId) {
        return mediaMapper.deleteByPrimaryKey(mediaId);
    }

    @Override
    public int insert(Media record) {
        return mediaMapper.insert(record);
    }

    @Override
    public int insertSelective(Media record) {
        return mediaMapper.insertSelective(record);
    }

    @Override
    public Media selectByPrimaryKey(Integer mediaId) {
        return mediaMapper.selectByPrimaryKey(mediaId);
    }

    @Override
    public int updateByPrimaryKeySelective(Media record) {
        return mediaMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Media record) {
        return mediaMapper.updateByPrimaryKey(record);
    }


    @Override
    public boolean isExistByMediaUrlAndTrueFile(String mediaUrl) {
         if(mediaMapper.findByMediaUrl(mediaUrl) == null){
             return false;
         }
         String path = FileUtil.getUploadFilePath(mediaUrl);
         if (!FileUtil.isFileExist(path)){
             return false;
         }
         return true;
    }
}
