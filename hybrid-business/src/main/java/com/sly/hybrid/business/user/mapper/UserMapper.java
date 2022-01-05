package com.sly.hybrid.business.user.mapper;

import com.sly.hybrid.business.user.model.po.User;

/**
 * 用户 mapper
 *
 * @author SLY
 * @date 2022/1/2
 */
public interface UserMapper {

    /**
     * 根据账号查询用户信息
     *
     * @param account 账号
     * @return {@link User}
     * @author SLY
     * @date 2022/1/2
     */
    User findUserByAccount(String account);

    /**
     * 新增
     *
     * @param user {@link User}
     * @return int
     * @author SLY
     * @date 2022/1/4
     */
    int add(User user);
}
