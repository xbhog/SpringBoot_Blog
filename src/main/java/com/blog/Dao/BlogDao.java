package com.blog.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.Pojo.Blog;
import com.blog.Pojo.BlogAndTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogDao extends BaseMapper<Blog> {
    //按条件查询
    //List<Blog> getAllBlogs();
    IPage<Blog> getAllBlogs(IPage<Blog> page);
    List<Blog> getAllBlog();
    int saveBlog(Blog blog);
    int deleteBlog(Long id);//删除博客
    int updateBlog(Blog blog); //更新博客
    //前端页面展示
    IPage<Blog> getIndexBlog(IPage<Blog> page);
//    List<Blog> getIndexBlog();
    //前端页面展示推荐
    List<Blog> getAllRecommendBlog();
    //全局搜索
    IPage<Blog> getsearchBlog(IPage<Blog> page,@Param("query") String query);
    //前端获取博客详情ID
    Blog getDetailBlog(@Param("id") Long id);
    //决定页面的标签显示
    int saveBlogAndTag(BlogAndTag blogAndTag);
    //通过typeid查询所有的博客getByTagId
    IPage<Blog> getByTypeId(IPage<Blog> page,@Param("id") Long id);
    IPage<Blog> getByTagId(IPage<Blog> page,@Param("id") Long id);

    @Select("select DATE_FORMAT(b.update_time, '%Y') from t_blog b order by b.update_time desc")
    List<String> findGroupYear();

    @Select("select b.title, b.update_time, b.id, b.flag\n" +
            "        from t_blog b\n" +
            "        where DATE_FORMAT(b.update_time, \"%Y\") = #{year}")
    List<Blog> findByYear(@Param("year") String year);  //按年份查询博客
}
