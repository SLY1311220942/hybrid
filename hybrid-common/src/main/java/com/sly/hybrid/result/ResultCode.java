package com.sly.hybrid.result;

import com.sly.myplugin.base.result.IResultCode;

/**
 * 返回码
 *
 * @author SLY
 * @date 2021/12/30
 */
public enum ResultCode implements IResultCode {
    /** 缺少登录token */
    LOGIN_TOKEN_NULL("100001", "登录信息缺失，请重新登录！"),
    /** 缓存中找不到登录token对应的信息 */
    LOGIN_TOKEN_MISS("100002", "登录信息过期，请重新登录！"),
    ;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final String code;
    private final String msg;

    @Override
    public String getCode() {
        return code;
    }
    @Override
    public String getMsg() {
        return msg;
    }
}
