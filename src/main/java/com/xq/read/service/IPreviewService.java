package com.xq.read.service;

import com.xq.read.common.ServerResponse;
import com.xq.read.vo.PreviewVo;

import java.util.List;

public interface IPreviewService {

    /**游客可以访问，返回主页面
     *
     * @return
     */
    ServerResponse getIndex(Integer page);

    /**查询相关标签文章目录
     *
     * @param tagIds
     * @return
     */
    ServerResponse getArticlesByTags(List<Integer> tagIds, Integer page);

    /**查询某用户文章目录
     *
     * @param userId
     * @return
     */
    ServerResponse getArticlesByUserId(Integer userId, Integer page);

    /**搜索关键字查询文章目录
     *
     * @param keyword
     * @return
     */
    ServerResponse getArticlesByKeyWord(String  keyword, Integer page);

    /**获取某用户收藏夹目录
     *
     * @param userId
     * @return
     */
    ServerResponse getCollectArticles(Integer  userId, Integer page);

}
