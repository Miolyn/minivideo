package cn.tju.minivideo.service;

import java.util.List;

public interface AlgorithmService {

    public void updateModelOnCreateVideo();

    public void updateModelOnNewHistory();

    public List<Integer> getRecommendByUId(Integer uid);
}
