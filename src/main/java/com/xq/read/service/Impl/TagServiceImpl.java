package com.xq.read.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.read.pojo.Dict_tag;
import com.xq.read.pojo.Tag;
import com.xq.read.mapper.TagMapper;
import com.xq.read.service.IDict_tagService;
import com.xq.read.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章标签 服务实现类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Autowired
    TagMapper tagMapper;

    @Autowired
    IDict_tagService iDict_tagService;



    public List<Tag> tags(int articleId) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("articleId",articleId);
        List<Tag> tags = tagMapper.selectList(wrapper);
        return tags;
    }

    @Override
    public List<String> tagNames(int articleId) {
        List<Tag> tags = tags(articleId);
        List<Dict_tag> tagInfo = iDict_tagService.getTagInfoByTagId(tags);
        List<String> nameList = new ArrayList<>();

        for (Dict_tag dict_tag : tagInfo) {
            nameList.add(dict_tag.getTagName());
        }

        return nameList;
    }

    @Override
    public Boolean insertTag(List<Tag> tags) {
        Boolean b = true;
        for (Tag tag : tags) {
            int insert = tagMapper.insert(tag);
            b = b && (insert>0);
        }
        return b;
    }


}
