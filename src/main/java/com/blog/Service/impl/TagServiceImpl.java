package com.blog.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.Dao.TagDao;
import com.blog.Pojo.Tag;
import com.blog.Pojo.Type;
import com.blog.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public Tag getNameTag(String name) {
        return tagDao.getNameTag(name);
    }

    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }
    @Override
    public List<Tag> getTagByString(String text) {    //从tagIds字符串中获取id，根据id获取tag集合
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long long1 : longs) {
            tags.add(tagDao.getTag(long1));
        }
        return tags;
    }

    private List<Long> convertToList(String ids) {  //把前端的tagIds字符串转换为list集合
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public List<Tag> getBlogTag() {
        return tagDao.getBlogTag();
    }
}
