package com.blog.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.Pojo.Tag;
import com.blog.Pojo.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagService extends IService<Tag> {
    public Tag getNameTag(String name);
    public int saveTag(Tag tag);
    public int updateTag(Tag tag);
    List<Tag> getAllTag();
    List<Tag> getTagByString(String text);   //从字符串中获取tag集合
    Tag getTag(Long id);
    List<Tag> getBlogTag();
}
