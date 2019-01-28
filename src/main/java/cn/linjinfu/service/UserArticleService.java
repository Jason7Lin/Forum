package cn.linjinfu.service;

import cn.linjinfu.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @author TyCoding
 * @date 2018/5/3 上午8:36
 */
public interface UserArticleService {

    PageBean<Article> findByPage(Article article, int pageCode, int pageSize);

    List<Module> selectModuleAll();

    void insertArticle(Article article);

    void deleteArticle(int r_id);

    Article selectArticleById(int r_id);

    void updateArticle(Article article);

    List<Words> selectArticleWords();

    void insertArticleWords(Words words);

    void deleteArticleWords(int lw_id);

}
