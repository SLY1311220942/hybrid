package com.sly.hybrid.business.blog.service;

import com.sly.hybrid.business.blog.mapper.BlogMapper;
import com.sly.hybrid.business.blog.model.po.Blog;
import com.sly.hybrid.business.blog.param.BlogDetailFindParam;
import com.sly.myplugin.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 博客 service impl
 *
 * @author SLY
 * @date 2021/12/30
 */
@Slf4j
@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;


    /**
     * 查询博客详情
     *
     * @param param 查询博客详情参数
     * @return {@link Result}<{@link Blog}>
     * @author SLY
     * @date 2021/12/30
     */
    public Result<Blog> findBlogDetail(BlogDetailFindParam param) {


        return Result.success();
    }
}
