package com.blog.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.Pojo.Blog;
import com.blog.Pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeDao extends BaseMapper<Type> {
    //增删改 返回的值类型是int或者void
    int saveType(Type type);  //保存标签
    Type getType(@Param("id") Long id);  //根据Id进行查询
    int updateType(Type type);  //更新标签
    List<Type> getAllType();
    Type getNameType(@Param("name") String name);
    //在首页显示的type
    List<Type> getBlogType();
    //主页分类显示
    IPage<Type> getAllTypes(IPage<Type> page);
    List<Blog> getBlogTypeId(@Param("id") Long id);
}
