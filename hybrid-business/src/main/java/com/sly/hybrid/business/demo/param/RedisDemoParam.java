package com.sly.hybrid.business.demo.param;

import com.sly.hybrid.param.BaseParam;
import com.sly.myplugin.validate.annotation.IntValue;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * RedisDemo 参数
 *
 * @author SLY
 * @date 2021/12/3
 */
@Setter
@Getter
public class RedisDemoParam extends BaseParam {
    @NotNull
    @IntValue(message = "非法的redis源", values = {1, 2, 3, 4})
    private Integer redisSource;
    @NotBlank(message = "键不能为空！")
    private String key;
    @NotBlank(message = "值不能为空！")
    private String value;
}
