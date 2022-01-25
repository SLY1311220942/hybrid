package com.sly.hybrid.business.dict.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 账号激活扩展字段dto
 *
 * @author SLY
 * @date 2022/1/6
 */
@Setter
@Getter
public class AccountActiveExtDto implements Serializable {
    /**
     * 激活url
     */
    private String activeUrl;
}
