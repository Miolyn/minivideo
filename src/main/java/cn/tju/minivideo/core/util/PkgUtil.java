package cn.tju.minivideo.core.util;

import cn.tju.minivideo.core.constants.MsgEnums;
import cn.tju.minivideo.core.exception.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PkgUtil {
    public static String execPython(String filePath, String ...args){
        if(!FileUtil.isFileExist(filePath)){
            throw new ServiceException(MsgEnums.FAIL);
        }
        String python = "python";
        List<String> commandLine = new ArrayList<>();
        commandLine.add(python);
        commandLine.add(filePath);
        commandLine.addAll(Arrays.asList(args));

        try {
            Process process = Runtime.getRuntime().exec((String[]) commandLine.toArray());
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ServiceException(MsgEnums.FAIL);
    }
}
