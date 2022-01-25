package com.sly.hybrid.business.email.model.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 邮件模板
 *
 * @author SLY
 * @date 2022/1/6
 */
@Setter
@Getter
public class EmailTemplate implements Serializable {
    /** int '邮件模板ID' */
    private Integer id;

    /** varchar(32) '模板名称' */
    private String name;

    /** varchar(100) '邮件标题' */
    private String title;

    /** text '邮件内容' */
    private String content;

    /** varchar(20) '邮件类型' */
    private String type;

    /** tinyint '删除状态（0.有效，1.删除）' */
    private Integer delStatus;
}
