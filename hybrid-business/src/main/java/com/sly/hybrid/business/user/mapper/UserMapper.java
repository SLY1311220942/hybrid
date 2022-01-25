package com.sly.hybrid.business.user.mapper;

import com.sly.hybrid.business.user.model.po.User;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 修改用户状态
     *
     * @param id         用户ID
     * @param userStatus 用户状态
     * @return int
     * @author SLY
     * @date 2022/1/8
     */
    int updateUserStatus(@Param("id") Integer id, @Param("userStatus") String userStatus);

    /**
     * 根据id查询用户信息
     *
     * @param id 用户ID
     * @return {@link User}
     * @author SLY
     * @date 2022/1/25
     */
    User findById(Integer id);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return int
     * @author SLY
     * @date 2022/1/25
     */
    int updateUserInfo(User user);
}
