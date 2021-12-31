package com.sly.hybrid.login;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户token对象
 *
 * @author SLY
 * @date 2021/12/30
 */
@Setter
@Getter
public class UserToken implements Serializable {
    /** 用户ID */
    private Integer id;

    /** 用户名称 */
    private String username;

    /** 密码 */
    private String password;

    /** 头像地址 */
    private String headPortrait;

    /** 简介 */
    private String briefIntroduction;

    /** 真实姓名 */
    private String realName;

    /** 身份证号 */
    private String idCard;

    /** 性别（0.女，1.男） */
    private Integer gender;

    /** 邮箱 */
    private String email;

    /** 手机 */
    private String phone;

    /** 生日 */
    private Date birthday;

    /**
     * 用户状态
     * {@link com.sly.hybrid.constant.StatusConst.UserStatus}
     */
    private String userStatus;

}
