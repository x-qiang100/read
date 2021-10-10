package com.xq.read.service;

import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xq.read.vo.PreviewVo;

import java.util.List;

/**
 * <p>
 * 收藏夹 服务类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
public interface ICollectionService extends IService<Collection> {

    /**获取某用户收藏夹文章id
     *
     * @param userId
     * @return
     */
    List<Integer> getArticles(Integer userId);



    /**
     * 插入一条收藏记录
     * @param collection
     * @return
     */
    Boolean insertCollect(Collection collection);

    /**
     * 删除一条收藏记录
     * @param collection
     * @return
     */
    Boolean deleteCollect(Collection collection);

}
