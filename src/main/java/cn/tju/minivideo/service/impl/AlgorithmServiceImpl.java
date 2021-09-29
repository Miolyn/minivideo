package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.core.constants.AlgorithmConst;
import cn.tju.minivideo.core.util.HttpUtil;
import cn.tju.minivideo.core.util.JsonUtil;
import cn.tju.minivideo.service.AlgorithmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class AlgorithmServiceImpl implements AlgorithmService {

    @Override
    public void updateModelOnCreateVideo() {
        HttpUtil.httpGet(String.format(AlgorithmConst.Recommend.RecommendVideoUrl, AlgorithmConst.Recommend.typeUpdateVideo, AlgorithmConst.Recommend.Any));
    }

    @Override
    public void updateModelOnNewHistory() {
        HttpUtil.httpGet(String.format(AlgorithmConst.Recommend.RecommendVideoUrl, AlgorithmConst.Recommend.typeUpdateVideo, AlgorithmConst.Recommend.Any));
    }

    @Override
    public List<Integer> getVideoRecommendByUId(Integer uid) {
        return HttpUtil.httpGetReturnList(String.format(AlgorithmConst.Recommend.RecommendVideoUrl, AlgorithmConst.Recommend.Any, uid));
    }

    @Override
    public List<Integer> getGoodsRecommendByUId(Integer uid) {

        return HttpUtil.httpGetReturnList(String.format(AlgorithmConst.Recommend.RecommendGoodsUrl, AlgorithmConst.Recommend.Any, uid));
    }

    public String checkContentAndGetDealingContent(String content){
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("text", content);
        String res = HttpUtil.httpPost(AlgorithmConst.ContentCheck.ContentCheckUrl, param);
        Map<String, Object> map = JsonUtil.String2Map(res);
        if(!(Boolean) map.get(AlgorithmConst.ContentCheck.Result)){
            return (String) map.get(AlgorithmConst.ContentCheck.NewText);
        }
        return content;
    }

    @Override
    public Boolean checkPicture(String url) {
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("url", url);
        String res = HttpUtil.httpPost(AlgorithmConst.PictureCheck.PictureCheckUrl, param);
        Map<String, Object> map = JsonUtil.String2Map(res);
        log.info(map.toString());
        return Boolean.valueOf((String) map.get(AlgorithmConst.PictureCheck.Res));
    }
}
