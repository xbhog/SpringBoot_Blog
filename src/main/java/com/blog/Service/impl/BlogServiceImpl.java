package com.blog.Service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.Dao.BlogDao;
import com.blog.NotFoundException;
import com.blog.Pojo.Blog;
import com.blog.Pojo.BlogAndTag;
import com.blog.Pojo.Tag;
import com.blog.Service.BlogService;
import com.blog.Util.MarkDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
    public IPage<Blog> getAllBlogs(IPage<Blog> page) {
        return blogDao.getAllBlogs(page);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客
        blogDao.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());

        return blogDao.updateBlog(blog);
    }

    @Override
    public IPage<Blog> getIndexBlog(IPage<Blog> page) {
        return blogDao.getIndexBlog(page);
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public IPage<Blog> getsearchBlog(IPage<Blog> page, String query) {
        return blogDao.getsearchBlog(page,query);
    }

    @Override
    public Blog getDetailBlog(Long id) {
        Blog detailBlog = blogDao.getDetailBlog(id);
        if(detailBlog == null){
            throw new NotFoundException("该博客不存在");
        }
        Blog blog = null;
        blog = detailBlog;
        String content = blog.getContent();
        blog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public IPage<Blog> getByTypeId(IPage<Blog> page, Long id) {
        return blogDao.getByTypeId(page,id);
    }
    @Override
    public IPage<Blog> getByTagId(IPage<Blog> page, Long id) {
        return blogDao.getByTagId(page,id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> groupYear = blogDao.findGroupYear();
        Set<String> set = new HashSet<>(groupYear); //去除重复的年份
        Map<String,List<Blog>> map = new HashMap<>();
        for(String year : set){
            map.put(year,blogDao.findByYear(year));
        }
        return map;
    }
}
