package cn.tju.minivideo.core.util;


import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
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

    public static String httpPost(String url, MultiValueMap<String, String> params){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        String res = restTemplate.postForObject(url, requestEntity, String.class);
        return res;
    }
}
