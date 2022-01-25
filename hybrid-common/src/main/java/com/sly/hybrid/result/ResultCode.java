package com.sly.hybrid.result;

import com.sly.myplugin.base.result.IResultCode;

/**
 * 返回码
 *
 * @author SLY
 * @date 2021/12/30
 */
public enum ResultCode implements IResultCode {
    /* 登录 */
    /** 缺少登录token */
    LOGIN_TOKEN_NULL(Code.CODE_100001, "登录信息缺失，请重新登录！"),
    /** 缓存中找不到登录token对应的信息 */
    LOGIN_TOKEN_MISS(Code.CODE_100002, "登录信息过期，请重新登录！"),
    /** 登录账号不存在 */
    LOGIN_ACCOUNT_MISS(Code.CODE_100003, "用户名或密码错误！"),
    /** 登录密码错误 */
    LOGIN_PASSWORD_ERROR(Code.CODE_100004, "用户名或密码错误！"),
    /* 注册 */
    /** 注册账号已存在 */
    REGISTER_ACCOUNT_EXIST(Code.CODE_101001, "该账号已被注册，请重新选择账号！"),
    /** 注册账号邮箱格式不合法 */
    REGISTER_ACCOUNT_EMAIL_ILLEGAL(Code.CODE_101002, "注册账号邮箱格式不合法！"),
    /** 密码格式不正确 */
    REGISTER_PASSWORD_ILLEGAL(Code.CODE_101003, "密码格式不正确，密码必须包含数字和字母，长度为8-16位字符！"),
    /** 未找到待激活用户 */
    REGISTER_USER_ACTIVE_UN_FIND(Code.CODE_101004, "未找到待激活用户！"),
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

    private static class Code {
        /** 编号(为了保证号码的唯一) */
        public static final String CODE_100001 = "100001";
        public static final String CODE_100002 = "100002";
        public static final String CODE_100003 = "100003";
        public static final String CODE_100004 = "100004";

        public static final String CODE_101001 = "101001";
        public static final String CODE_101002 = "101002";
        public static final String CODE_101003 = "101003";
        public static final String CODE_101004 = "101004";
    }
}
