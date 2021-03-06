package cn.tju.minivideo.core.util;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static <T> String List2String(List<T> list){
        try {
            return mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new ServiceException(MsgEnums.INTERNAL_ERROR);
        }
    }

    public static <T> List<T> String2List(String jsonList, Class<T> clazz){
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return mapper.readValue(jsonList, javaType);
        } catch (JsonProcessingException e) {
            throw new ServiceException(MsgEnums.INTERNAL_ERROR);
        }
    }

    public static String Object2String(Object o){
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new ServiceException(MsgEnums.INTERNAL_ERROR);
        }
    }

    public static <T> Object String2Object(String content, Class<T> clazz){
        try {
            return mapper.readValue(content, clazz);
        } catch (JsonProcessingException e) {
            throw new ServiceException(MsgEnums.INTERNAL_ERROR);
        }
    }

    public static Map<String, Object> String2Map(String res){
        try {
            Map<String, Object> tmpMap = mapper.readValue(res, Map.class);
            return tmpMap;
        } catch (JsonProcessingException e) {
            throw new ServiceException(MsgEnums.INTERNAL_ERROR);
        }

    }
}
