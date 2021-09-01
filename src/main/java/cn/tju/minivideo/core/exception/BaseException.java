package cn.tju.minivideo.core.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -997101946070796354L;

    protected Integer code;

    public BaseException(){}
    public BaseException(String message){
        super(message);
    }

    public BaseException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public Integer getCode(){
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
