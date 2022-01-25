package com.sly.hybrid.business.email.mapper;

import com.sly.hybrid.business.email.model.po.EmailTemplate;

/**
 * 邮件模板mapper
 *
 * @author SLY
 * @date 2022/1/6
 */
public interface EmailTemplateMapper {

    /**
     * 根据类型查询邮件模板
     *
     * @param type 类型
     * @return {@link EmailTemplate}
     * @author SLY
     * @date 2022/1/6
     */
    EmailTemplate findByType(String type);
}
