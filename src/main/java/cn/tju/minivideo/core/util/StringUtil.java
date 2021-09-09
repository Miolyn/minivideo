package cn.tju.minivideo.core.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern topicTagPattern = Pattern.compile("#([^#]{1,40})#");

    public static List<String> getTopicList(String content){
        Set<String> topicList = new LinkedHashSet<>();
        Matcher matcher = topicTagPattern.matcher(content);
        while (matcher.find()){
            String topicName = matcher.group(1);
            topicList.add(topicName);
        }
        return new ArrayList<String>(topicList);
//        return topicList;
    }
}
