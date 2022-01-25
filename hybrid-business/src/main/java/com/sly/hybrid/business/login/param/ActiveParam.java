package com.sly.hybrid.business.login.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 激活参数
 *
 * @author SLY
 * @date 2022/1/6
 */
@Setter
@Getter
public class ActiveParam implements Serializable {

    /** 激活code */
    @NotBlank(message = "激活code不能为空！")
    private String activeCode;
    /** 用户ID */
    @NotNull(message = "用户ID不能为空！")
    private Integer userId;
}
