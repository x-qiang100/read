package com.xq.read.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Dict_tag;
import com.xq.read.mapper.Dict_tagMapper;
import com.xq.read.pojo.Tag;
import com.xq.read.service.IDict_tagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xq.read.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 标签字典 服务实现类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Service
public class Dict_tagServiceImpl extends ServiceImpl<Dict_tagMapper, Dict_tag> implements IDict_tagService {

    @Autowired
    Dict_tagMapper dict_tagMapper;

    @Override
    public List<Dict_tag> getTagInfoByTagId(List<Tag> tags) {
        List<Integer> list = new ArrayList<>();
        for (Tag tag : tags) {
            list.add(tag.getTagId());
        }
        List<Dict_tag> dict_tags = dict_tagMapper.selectBatchIds(list);
        return dict_tags;
    }

    @Override
    public Boolean insertTag(Dict_tag tag) {
        int insert = dict_tagMapper.insert(tag);
        return insert > 0;
    }

    @Override
    public ServerResponse<List<Dict_tag>> getTags() {
        QueryWrapper<Dict_tag> wrapper = new QueryWrapper<>();
        //
        List<Dict_tag> dict_tags = dict_tagMapper.selectList(wrapper);
        return ServerResponse.createBySuccess(dict_tags);
    }

    @Override
    public List<Integer> getIdByName(List<String> strings) {

        final List<Dict_tag> dict_tags = dict_tagMapper.selectList(null);
        final HashMap<String, Integer> hashMap = new HashMap<>();
        for (Dict_tag dict_tag : dict_tags) {
            hashMap.put(dict_tag.getTagName(), dict_tag.getId());
        }
        List<Integer> list = new ArrayList<>();
        for (String string : strings) {
            list.add(hashMap.get(string ));
        }
        return list;
    }
}
