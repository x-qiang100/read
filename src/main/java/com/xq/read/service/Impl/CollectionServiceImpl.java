package com.xq.read.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.read.common.ServerResponse;
import com.xq.read.mapper.ArticleMapper;
import com.xq.read.pojo.Collection;
import com.xq.read.mapper.CollectionMapper;
import com.xq.read.service.ICollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xq.read.service.IPreviewService;
import com.xq.read.vo.PreviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 收藏夹 服务实现类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

    @Autowired(required = false)
    CollectionMapper collectionMapper;

    @Autowired(required = false)
    ArticleMapper articleMapper;

    @Override
    public List<Integer> getArticles(Integer userId) {
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        List<Collection> collections = collectionMapper.selectList(wrapper);
        List<Integer> list = new ArrayList<>();
        for (Collection collection : collections) {
            list.add(collection.getArticleId());
        }
        return list;
    }

    @Override
    public Boolean insertCollect(Collection collection) {
        int i = collectionMapper.insert(collection);
        final Integer j = articleMapper.updateByColumn("collect", collection.getArticleId());
        return i>0 && j>0;
    }

    @Override
    public Boolean deleteCollect(Collection collection) {
        QueryWrapper<Collection> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",collection.getUserId());
        wrapper.eq("articleId",collection.getArticleId());
        final int delete = collectionMapper.delete(wrapper);
        return delete > 0;
    }
}
