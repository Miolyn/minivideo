package cn.tju.minivideo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.tju.minivideo.entity.BulletScreen;
import cn.tju.minivideo.dao.BulletScreenMapper;
import cn.tju.minivideo.service.BulletScreenService;

import java.util.List;

@Service
public class BulletScreenServiceImpl implements BulletScreenService {

    @Resource
    private BulletScreenMapper bulletScreenMapper;

    @Override
    public int deleteByPrimaryKey(Integer bulletScreenId) {
        return bulletScreenMapper.deleteByPrimaryKey(bulletScreenId);
    }

    @Override
    public int insert(BulletScreen record) {
        return bulletScreenMapper.insert(record);
    }

    @Override
    public int insertSelective(BulletScreen record) {
        return bulletScreenMapper.insertSelective(record);
    }

    @Override
    public BulletScreen selectByPrimaryKey(Integer bulletScreenId) {
        return bulletScreenMapper.selectByPrimaryKey(bulletScreenId);
    }

    @Override
    public int updateByPrimaryKeySelective(BulletScreen record) {
        return bulletScreenMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BulletScreen record) {
        return bulletScreenMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BulletScreen> findAllByVideoId(Integer videoId) {
        return bulletScreenMapper.findByVideoId(videoId);
    }

    @Override
    public PageInfo<BulletScreen> findByVideoIdWithPaginator(Integer videoId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo<BulletScreen> pageInfo = new PageInfo<>(bulletScreenMapper.findByVideoId(videoId));
        return pageInfo;
    }

}


