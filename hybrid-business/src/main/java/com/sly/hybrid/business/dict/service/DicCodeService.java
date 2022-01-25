package com.sly.hybrid.business.dict.service;

import com.github.pagehelper.PageInfo;
import com.sly.hybrid.business.dict.mapper.DicCodeMapper;
import com.sly.hybrid.business.dict.model.po.DicCode;
import com.sly.myplugin.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典 service
 *
 * @author SLY
 * @date 2022/1/6
 */
@Slf4j
@Service
public class DicCodeService {

    @Resource
    private DicCodeMapper dicCodeMapper;

    /**
     * 根据父节点code查询下级节点列表
     *
     * @param code 父节点code
     * @return {@link Result}<{@link PageInfo}<{@link DicCode}>>
     * @author SLY
     * @date 2022/1/25
     */
    public Result<PageInfo<DicCode>> findChildrenByCode(String code) {
        List<DicCode> list = dicCodeMapper.findChildrenByCode(code);
        return Result.success(new PageInfo<>(list));
    }
}
