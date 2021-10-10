package com.xq.read.service;

import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xq.read.vo.ArticleVo;
import com.xq.read.vo.PreviewVo;

import java.util.List;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
public interface IArticleService extends IService<Article> {

    /**
     * 根据文章id获取文章视图信息
     * @param id
     * @param userId
     * @return
     */
    ServerResponse<ArticleVo> getArticleVoById(Integer id, Integer userId);

    /**根据文章id获取文章信息
     *
     * @param id
     * @return
     */
    Article getArticleById(Integer id);


    /**根据文章id集合获取文章列表
     *
     * @param integerList
     * @return
     */
    List<Article> getArticlesByArticleId(List<Integer> integerList);

    /**
     * 插入文章
     * @param article
     * @return
     */
    Boolean insertArticle(Article article, List<Integer > tags);


    /**
     * 点赞文章
     * @param articleId
     * @return
     */
    Boolean updateArticleLike(Integer articleId);


    /**
     * 删除文章
     *
     * @param userId
     * @param articleId
     * @return
     */
    Boolean deleteArticle(Integer userId, Integer articleId);


}
