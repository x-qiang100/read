package com.xq.read;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.read.common.ServerResponse;
import com.xq.read.mapper.ArticleMapper;
import com.xq.read.mapper.CommentMapper;
import com.xq.read.mapper.UserMapper;
import com.xq.read.pojo.Article;
import com.xq.read.pojo.Comment;
import com.xq.read.pojo.User;
import com.xq.read.service.IArticleService;
import com.xq.read.service.IPreviewService;
import com.xq.read.service.IUserService;
import com.xq.read.vo.ArticleVo;
import com.xq.read.vo.PreviewVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ReadApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    IUserService iUserService;

    @Test
    void testRegister() {
//        User user = new User();
//        user.setName("xq");
//        user.setPwd("123456");
        ServerResponse r = iUserService.register("xq", "123456");
        System.out.println("r.getData() = " + r.getData());
        System.out.println("r.getMsg() = " + r.getMsg());
    }

    @Test
    void testLogin() {
        ServerResponse r = iUserService.login("xq", "123456");
        System.out.println("r.getData() = " + r.getData());
        System.out.println("r.getMsg() = " + r.getMsg());
    }

    @Autowired(required = false)
    CommentMapper commentMapper;
    @Test
    void testComment() {
        Comment comment = commentMapper.selectById(1);
        System.out.println("comment = " + comment);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
    }

    @Autowired
    UserMapper userMapper;

    @Test
    void testCount() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Integer integer = userMapper.selectCount(wrapper);
        System.out.println("integer = " + integer);
    }
    //评论视图
    //tag查询
    //查询count，刚刚添加的数据库id

    @Autowired
    IArticleService iArticleService;
    @Test
    void testGetArticle() {
        ServerResponse<ArticleVo> articleVoById = iArticleService.getArticleVoById(1, -1);
        System.out.println("articleVoById.getData() = " + articleVoById.getData().getCommentList());
    }

    @Autowired
    ArticleMapper articleMapper;
    @Test
    void testArticlePage1(){
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",1);
        Page<Article> page = new Page<>(1,10);
        Page<Article> articles = articleMapper.selectPage(page, null);
        List<Article> records = articles.getRecords();
        long total = articles.getTotal();

        Page<Article> page1 = articles.setCurrent(1);
        List<Article> records1 = page1.getRecords();
        System.out.println("records1.size() = " + records1.size());
        for (Article article : records1) {
            System.out.println("article.getTopic() = " + article.getTopic());
        }

        System.out.println("total = " + total);
        System.out.println("articles = " + records.size());
        for (Article record : records) {
            System.out.println("record.getTopic() = " + record.getTopic());
        }
//        System.out.println("articles = " + articles.size());

    }
    @Test
    void testArticle() {
        List<Article> articles = articleMapper.selectListPage(0, 10);
        for (Article article : articles) {
            System.out.println("article.getTopic() = " + article.getTopic());
            System.out.println("article.getTopic() = " + article.getId());
        }
    }



        @Autowired
    IPreviewService iPreviewService;
    @Test
    void testPreview(){
        ServerResponse<List<PreviewVo>> articlesByUserId = iPreviewService.getIndex(2 );
        System.out.println("articlesByUserId.getData() = " + articlesByUserId.getData());
    }

    @Test
    void testUserMapper(){
        final Integer integer = userMapper.updateBy("likes", 2);
        System.out.println("integer = " + integer);
    }

    @Test
    void delete(){
        Boolean b = iArticleService.deleteArticle(4,16);
        System.out.println("integer = " + b);
    }


}
