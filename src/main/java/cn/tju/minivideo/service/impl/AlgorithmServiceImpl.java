package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.AlgorithmConst;
import cn.tju.minivideo.core.util.HttpUtil;
import cn.tju.minivideo.service.AlgorithmService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    @Override
    public void updateModelOnCreateVideo() {
        HttpUtil.httpGet(String.format(AlgorithmConst.RecommendUrl, AlgorithmConst.typeUpdateVideo, AlgorithmConst.Any));
    }

    @Override
    public void updateModelOnNewHistory() {
        HttpUtil.httpGet(String.format(AlgorithmConst.RecommendUrl, AlgorithmConst.typeUpdateVideo, AlgorithmConst.Any));
    }

    @Override
    public List<Integer> getRecommendByUId(Integer uid) {
        return HttpUtil.httpGetReturnList(String.format(AlgorithmConst.RecommendUrl, AlgorithmConst.Any, uid));
    }
}
