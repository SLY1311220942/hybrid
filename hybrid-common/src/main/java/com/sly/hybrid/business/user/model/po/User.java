package com.sly.hybrid.business.user.model.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务用户 model
 *
 * @author SLY
 * @date 2021/11/22
 */
@Setter
@Getter
public class User implements Serializable {

    /** int '用户ID' */
    private Integer id;

    /** varchar(64) '账号' */
    private String account;

    /** varchar(32) '用户名称' */
    private String username;

    /** varchar(32) '密码' */
    private String password;

    /** varchar(255) '头像地址' */
    private String headPortrait;

    /** varchar(255) '简介' */
    private String briefIntroduction;

    /** varchar(32) '真实姓名' */
    private String realName;

    /** varchar(24) '身份证号' */
    private String idCard;

    /** tinyint '性别（0.女，1.男）' */
    private Integer gender;

    /** varchar(64) '邮箱' */
    private String email;

    /** varchar(24) '手机' */
    private String phone;

    /** date '生日' */
    private Date birthday;

    /** varchar(10) '用户状态（{@link com.sly.hybrid.constant.StatusConst.UserStatus}）' */
    private String userStatus;

    /** datetime '创建时间' */
    private Date createTime;

    /** datetime '创建时间' */
    private Date updateTime;

    /** tinyint '删除状态（0.有效，1.删除）' */
    private Integer delStatus;
}
