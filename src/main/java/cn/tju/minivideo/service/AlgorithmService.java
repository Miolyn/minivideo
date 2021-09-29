package cn.tju.minivideo.service;

import java.util.List;

public interface AlgorithmService {

    public void updateModelOnCreateVideo();

    public void updateModelOnNewHistory();

    public List<Integer> getVideoRecommendByUId(Integer uid);
    public List<Integer> getGoodsRecommendByUId(Integer uid);

    public String checkContentAndGetDealingContent(String content);

    public Boolean checkPicture(String url);
}
