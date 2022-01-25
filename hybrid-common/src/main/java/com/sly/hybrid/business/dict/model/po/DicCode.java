package com.sly.hybrid.business.dict.model.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 字典码 model
 *
 * @author SLY
 * @date 2021/11/22
 */
@Setter
@Getter
public class DicCode implements Serializable {

    /** int '字典ID' */
    private Integer id;

    /** varchar(20) '字典code' */
    private String code;

    /** varchar(32) '名称' */
    private String codeName;

    /** varchar(20) '父节点code' */
    private String parentCode;

    /** json '扩展字段' */
    private String extended;

    /** int '排序字段（ASC）' */
    private Integer sort;

    /** tinyint '删除状态（0.有效，1.删除）' */
    private Integer delStatus;
}
