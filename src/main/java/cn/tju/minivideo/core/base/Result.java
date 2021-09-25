package cn.tju.minivideo.core.base;

import cn.tju.minivideo.core.constants.MsgEnums;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 1430633339880116031L;

    private Integer code;

    private String msg;

    private String token;

    private Object data;


    public Result(Integer code, String msg, String token, Object data) {
        this.code = code;
        this.msg = msg;
        this.token = token;
        this.data = data;
    }

    public Result(MsgEnums msgEnums, Object data){
        this.code = msgEnums.code();
        this.msg = msgEnums.desc();
        this.token = "";
        this.data = data;
    }

    public Result(MsgEnums msgEnums){
        this.code = msgEnums.code();
        this.msg = msgEnums.desc();
        this.token = "";
        this.data = null;
    }

    public Result(MsgEnums msgEnums, String token){
        this.code = msgEnums.code();
        this.msg = msgEnums.desc();
        this.token = token;
        this.data = null;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                ", data=" + data +
                '}';
    }
}
