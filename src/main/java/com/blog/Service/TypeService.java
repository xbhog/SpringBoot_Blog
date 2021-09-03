package com.blog.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.Pojo.Type;

import java.util.List;

public interface TypeService  extends IService<Type> {
    public int saveType(Type type);  //保存标签
    public Type getType(Long id);  //根据Id进行查询
    public int updateType(Type type);  //更新标签
    public List<Type> getAllType();
    public Type getNameType(String name);
    //在首页显示的type
    List<Type> getBlogType();
    IPage<Type> getAllTypes(IPage<Type> page);
}
