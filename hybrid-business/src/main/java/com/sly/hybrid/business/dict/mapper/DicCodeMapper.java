package com.sly.hybrid.business.dict.mapper;

import com.sly.hybrid.business.dict.model.po.DicCode;

import java.util.List;

/**
 * 数据字典mapper
 *
 * @author SLY
 * @date 2022/1/6
 */
public interface DicCodeMapper {

    /**
     * 根据code查询数据字典
     *
     * @param code code
     * @return {@link DicCode}
     * @author SLY
     * @date 2022/1/6
     */
    DicCode findByCode(String code);

    /**
     * 根据父节点code查询下级节点列表
     *
     * @param code 父节点code
     * @return {@link List}<{@link DicCode}>
     * @author SLY
     * @date 2022/1/25
     */
    List<DicCode> findChildrenByCode(String code);
}
