package com.blog.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import com.blog.Pojo.Blog;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface BlogService extends IService<Blog>  {
    IPage<Blog> getAllBlogs(IPage<Blog> page);
    List<Blog> getAllBlog();
    int saveBlog(Blog blog);
    int deleteBlog(Long id);
    int updateBlog(Blog blog); //更新博客
    IPage<Blog> getIndexBlog(IPage<Blog> page);
    //前端页面推荐展示
    List<Blog> getAllRecommendBlog();  //推荐博客展示
    //全局搜索
    IPage<Blog> getsearchBlog(IPage<Blog> page,@Param("query") String query);
    //博客详情
    Blog getDetailBlog(Long id);
    //通过typeid查询所有的博客
    IPage<Blog> getByTypeId(IPage<Blog> page,@Param("id") Long id);
    IPage<Blog> getByTagId(IPage<Blog> page,@Param("id") Long id);

    Map<String, List<Blog>> archiveBlog();
}
