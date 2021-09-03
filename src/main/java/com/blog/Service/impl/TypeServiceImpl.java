package com.blog.Service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.Dao.TypeDao;
import com.blog.Pojo.Type;
import com.blog.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeDao,Type> implements TypeService {
    @Autowired
    private TypeDao typeDao;

//  添加事务注解
    @Transactional
    @Override
    public int saveType(Type type) {  //保存分类名
        return typeDao.saveType(type);
    }
    @Transactional
    @Override
    public Type getType(Long id) { //通过Id查询分类名
        return typeDao.getType(id);
    }
    @Transactional
    @Override
    public int updateType(Type type) { //更新分类信息
        return typeDao.updateType(type);
    }

    @Override
    public List<Type> getAllType() {

        return typeDao.getAllType();
    }

    @Override
    public Type getNameType(String name) {
        return typeDao.getNameType(name);
    }

    @Override
    public List<Type> getBlogType() {
        return typeDao.getBlogType();
    }
    //主页分类展示

    @Override
    public IPage<Type> getAllTypes(IPage<Type> page) {
        return typeDao.getAllTypes(page);
    }
}
