package com.xq.read.service;

import com.xq.read.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章标签 服务类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
public interface ITagService extends IService<Tag> {

    /**根据文章id获取标签信息
     *
     * @param articleId
     * @return
     */
    List<String> tagNames(int articleId);

    /**
     * 给文章添加标签
     * @param tag
     * @return
     */
    Boolean insertTag(List<Tag> tag);
}
