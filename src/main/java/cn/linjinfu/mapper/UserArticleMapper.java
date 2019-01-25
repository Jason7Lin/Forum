package cn.linjinfu.mapper;

import cn.linjinfu.pojo.Article;
import cn.linjinfu.pojo.Module;
import cn.linjinfu.pojo.Words;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserArticleMapper {

    //PageHelper插件的page类
    Page<Article> findByPage(Article article);

    List<Module> selectModuleAll();

    void insertArticle(Article article);

    void deleteArticle(int r_id);

    Article selectArticleById(int r_id);

    void updateArticle(Article article);

    List<Words> selectArticleWords();

    void insertArticleWords(Words words);

    void deleteArticleWords(int lw_id);
}
