package cn.linjinfu.service.impl;

import cn.linjinfu.mapper.UserArticleMapper;
import cn.linjinfu.pojo.*;
import cn.linjinfu.service.UserArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserArticleServiceImpl implements UserArticleService {

    /**
     * 注入Mapper层
     */
    @Autowired
    private UserArticleMapper mapper;

    @Override
    public void deleteArticle(int r_id) {
        mapper.deleteArticle(r_id);
    }

    @Override
    public Article selectArticleById(int r_id) {
        return mapper.selectArticleById(r_id);
    }

    @Override
    public void updateArticle(Article article) {
        mapper.updateArticle(article);
    }

    @Override
    public List<Words> selectArticleWords() {
        return mapper.selectArticleWords();
    }

    @Override
    public void insertArticleWords(Words words) {
        mapper.insertArticleWords(words);
    }

    @Override
    public void deleteArticleWords(int lw_id) {
        mapper.deleteArticleWords(lw_id);
    }

    public PageBean<Article> findByPage(Article article, int pageCode, int pageSize) {
        //使用倒序
        String orderBy = "r_id" + " desc";
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode, pageSize, orderBy);

        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        //PageHelper插件的page类
        Page<Article> page = mapper.findByPage(article);

        //返回封装好的PageBean
        return new PageBean<Article>(pageCode, (int) Math.ceil((double) (page.getTotal() / (double) pageSize)), (int) page.getTotal(), pageSize, page.getResult());
    }


    @Override
    public List<Module> selectModuleAll() {
        return mapper.selectModuleAll();
    }


    @Override
    public void insertArticle(Article article) {
        mapper.insertArticle(article);
    }



















    public void saveArticle(Article article) {

    }

    public void delete(int r_id) {

    }

    public void update(Article article) {

    }

    public Article findById(int r_id) {
        return null;
    }

    public void clean(int r_id) {

    }

    public void restore(int r_id) {

    }

    public void saveWords(Words words) {

    }

    public void saveReply(Reply reply) {

    }

    public List<Words> findByWords() {
        return null;
    }

    public List<Reply> findByReply() {
        return null;
    }
}
