package com.sly.hybrid.business.demo.param;

import com.sly.myplugin.validate.annotation.IntValue;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * ValidateDemo 参数
 *
 * @author SLY
 * @date 2021/11/29
 */
@Setter
@Getter
public class ValidateDemoParam implements Serializable {
    @NotNull(message = "ID不能为空！")
    private Integer id;
    @NotBlank(message = "名称不能为空！")
    private String name;
    @NotNull(message = "number不能为空！")
    @IntValue(message = "非法的number参数！", values = {1, 2, 3})
    private Integer number;
    @Valid
    @Size(message = "子列表不能为空！", min = 1)
    private List<Children> childrenList;
}

/**
 * 子类
 *
 * @author SLY
 * @date 2021/12/3
 */
@Setter
@Getter
class Children implements Serializable {
    @NotBlank(message = "子名称不能为空！")
    private String childrenName;
}
