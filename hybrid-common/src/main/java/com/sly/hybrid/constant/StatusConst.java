package com.sly.hybrid.constant;

/**
 * 状态常量类
 *
 * @author SLY
 * @date 2021/12/30
 */
public class StatusConst {
    /**
     * 删除状态:未删除
     */
    public static final int DEL_NO = 0;
    /**
     * 删除状态:已删除
     */
    public static final int DEL_YES = 1;

    /**
     * 性别:女
     */
    public static final int GENDER_WOMEN = 0;
    /**
     * 性别:男
     */
    public static final int GENDER_MEN = 1;

    /** 博客状态 */
    public enum BlogStatus {
        /** 草稿 */
        DRIFT("00000010", "草稿"),
        /** 审核 */
        VERIFY("00000020", "审核"),
        /** 发布 */
        PUBLISH("00000030", "发布"),
        ;
        private final String code;
        private final String name;

        BlogStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /** 用户状态 */
    public enum UserStatus {
        /** 草稿 */
        DRIFT("00001010", "待激活"),
        /** 审核 */
        VERIFY("00001020", "已激活"),
        /** 发布 */
        PUBLISH("00001030", "禁用"),
        ;
        private final String code;
        private final String name;

        UserStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
