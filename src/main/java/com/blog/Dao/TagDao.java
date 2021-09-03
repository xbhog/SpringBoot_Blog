package com.blog.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.Pojo.Tag;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagDao extends BaseMapper<Tag> {
    public Tag getNameTag(@Param("name") String name);
    public int saveTag(Tag tag);
    public int updateTag(Tag tag);
    List<Tag> getAllTag();  //为啥需要获取所有的标签呢，因为在博客(blog)的控制层需要设置。
    Tag getTag(@Param("id" )Long id);
    //前端显示tag
    List<Tag> getBlogTag();
}
