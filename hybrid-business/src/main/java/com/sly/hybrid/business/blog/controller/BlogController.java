package com.sly.hybrid.business.blog.controller;

import com.sly.hybrid.business.blog.model.po.Blog;
import com.sly.hybrid.business.blog.param.BlogDetailFindParam;
import com.sly.hybrid.business.blog.service.BlogService;
import com.sly.myplugin.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客 controller
 *
 * @author SLY
 * @date 2021/11/29
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 查询博客详情
     *
     * @param param 查询博客详情参数
     * @return {@link Result}<{@link Blog}>
     * @author SLY
     * @date 2021/12/30
     */
    @RequestMapping("/findBlogDetail")
    public Result<Blog> findBlogDetail(@Validated BlogDetailFindParam param) {
        return blogService.findBlogDetail(param);
    }
}
