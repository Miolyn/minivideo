package cn.tju.minivideo.core.util;


import org.springframework.web.client.RestTemplate;

import java.util.List;

public class HttpUtil {

    public static void httpGet(String url){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject(url, String.class);
    }

    public static List<Integer> httpGetReturnList(String url){
        RestTemplate restTemplate = new RestTemplate();

        String res = restTemplate.getForObject(url, String.class);
        return JsonUtil.String2List(res, Integer.class);
    }
}
