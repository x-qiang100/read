package com.xq.read.mapper;

import com.xq.read.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> selectListPage(Integer index, Integer size );

    List<Article> selectListPageOrderByLike(Integer index, Integer size );

    List<Article> selectListPageByUserId(Integer index, Integer size, Integer userId );

    List<Article> selectListPageByKeyword(Integer index, Integer size ,String keyword);

    Integer updateByColumn(String  column, Integer userId);


}
