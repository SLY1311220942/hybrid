package com.sly.hybrid.business.dict.controller;

import com.github.pagehelper.PageInfo;
import com.sly.hybrid.business.dict.model.po.DicCode;
import com.sly.hybrid.business.dict.service.DicCodeService;
import com.sly.myplugin.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据字典controller
 *
 * @author SLY
 * @date 2022/1/6
 */
@Slf4j
@RestController
@RequestMapping("/dicCode")
public class DicCodeController {

    @Autowired
    private DicCodeService dicCodeService;

    /**
     * 根据父节点code查询下级节点列表
     *
     * @param code 父节点code
     * @return {@link Result}<{@link PageInfo}<{@link DicCode}>>
     * @author SLY
     * @date 2022/1/25
     */
    @RequestMapping("/findChildrenByCode")
    public Result<PageInfo<DicCode>> findChildrenByCode(String code) {
        return dicCodeService.findChildrenByCode(code);
    }
}
