package com.ny.lg.common.constants;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class Result implements Serializable {
    /**
     * 是否成功
     */
    private boolean success = false;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 业务状态码，如果执行正常则返回200
     */
    private int code;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 不允许New，只能调用静态方法
     */
    private Result() {
    }

    /**
     * 方法处理成功后返回的结构封装
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(HttpStatus.OK.value());
        result.setData(data);
        result.setMessage(HttpStatus.OK.getReasonPhrase());
        return result;
    }

    /**
     * 发生异常时调用
     *
     * @param code 错误代码 参考{@link HttpStatus}
     * @param msg  错误信息
     * @return
     */
    public static Result error(int code, String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
