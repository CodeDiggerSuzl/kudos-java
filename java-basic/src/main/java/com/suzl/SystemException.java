package com.suzl;

/**
 * @author suzl
 * @date 2022/1/28 10:10 下午
 */
public class SystemException extends Exception {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public SystemException(int code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }

    private int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
