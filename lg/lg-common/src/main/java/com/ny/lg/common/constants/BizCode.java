package com.ny.lg.common.constants;

public class BizCode {
    /**
     * 登录成功
     */
    public static final BizCode LOGIN_SUCCESS = new BizCode(200, "登录成功");

    /**
     * 登出成功
     */
    public static final BizCode LOGOUT_SUCCESS = new BizCode(200, "登出成功");
    /**
     * Token为空
     */
    public static final BizCode TOKEN_IS_NULL = new BizCode(30001, "Token为空");
    /**
     * 非法Token
     */
    public static final BizCode ILLEGALITY_TOKEN = new BizCode(30002, "非法Token");
    /**
     * Token已过期
     */
    public static final BizCode EXPIRED_TOKEN = new BizCode(30003, "Token已过期");
    /**
     * Method not support
     */
    public static final BizCode AUTH_MEHTOD_NOT_SUPPORT = new BizCode(30004, "Method not support");
    /**
     * 参数不能为空
     */
    public static final BizCode PARAMS_IS_NULL = new BizCode(30005, "参数不能为空");
    /**
     * 系统繁忙，请稍后再试。
     */
    public static final BizCode SYSTEM_IS_BUSY = new BizCode(30006, "系统繁忙，请稍后再试。");
    /**
     * 处理成功!
     */
    public static final BizCode SUCCESS = new BizCode(0, "处理成功!");

    public BizCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
