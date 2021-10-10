package com.xq.read.service;

import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Dict_tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xq.read.pojo.Tag;

import java.util.List;

/**
 * <p>
 * 标签字典 服务类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
public interface IDict_tagService extends IService<Dict_tag> {

    /**
     * 通过tag的id集合获得tag的详细信息
     *
     * @param tags
     * @return
     */
    List<Dict_tag> getTagInfoByTagId(List<Tag> tags);

    /**
     * 插入一条tag信息
     * @param tag
     * @return
     */
    Boolean insertTag(Dict_tag tag);

    /**
     * 获取所有tag信息
     * @return
     */
    ServerResponse<List<Dict_tag>> getTags();

    /**
     * 标签名称转为标签id
     *
     * @param strings
     * @return
     */
    List<Integer> getIdByName(List<String> strings);
}
