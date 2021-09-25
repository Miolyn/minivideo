package cn.tju.minivideo.core.util;


import org.springframework.web.client.RestTemplate;

import java.util.List;

public class HttpUtil {

    public static List<String> httpGet(String url){
        RestTemplate restTemplate = new RestTemplate();

        String res = restTemplate.getForObject(url, String.class);
        return JsonUtil.String2List(res, String.class);
    }
}
