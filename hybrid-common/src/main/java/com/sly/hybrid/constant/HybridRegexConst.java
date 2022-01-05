package com.sly.hybrid.constant;

import com.sly.myplugin.base.regexp.RegexpConst;

/**
 * 项目正则常量类
 *
 * @author SLY
 * @date 2022/1/4
 */
public class HybridRegexConst extends RegexpConst {
    /** 用户密码正则验证 */
    public static final String USER_PASSWORD = "^(?=.*\\d)(?=.*[a-zA-Z]).{8,16}$";
}
