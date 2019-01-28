package cn.linjinfu.controller;

import cn.linjinfu.pojo.*;
import cn.linjinfu.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/adminArticle")
public class AdminArticleController {

    //还是用user那一套
    @Autowired
    private UserArticleService service;

    //去帖子管理
    @RequestMapping(value = "/toArticleManage.do")
    public String toArticleManage() {
        return "redirect:findByPage.do";
    }


    @RequestMapping("/findByPage.do")
    public String findByPage(Article article, HttpSession session, HttpServletRequest request, Model model,
                             @RequestParam(value = "pageCode", required = false, defaultValue = "1") int pageCode,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {

        //module数据
        List<Module> list = service.selectModuleAll();
        model.addAttribute("moduleList", list);
        //模块匹配
        //article由查询表单而来，再回显至查询表单
        model.addAttribute("article", article);
        //调用findByPage
        PageBean page = service.findByPage(article, pageCode, pageSize);
        model.addAttribute("page", page);
        return "view/admin_article/articleManage";
    }

    //去新增帖子
    @RequestMapping(value = "/toArticleWrite.do")
    public String toArticleWrite(Model model) {
        List<Module> list = service.selectModuleAll();
        model.addAttribute("moduleList", list);
        return "view/admin_article/articleWrite";
    }

    //新增帖子
    @RequestMapping(value = "/insertArticle.do")
    public String insertArticle(Article article, Model model) {
        try {
            service.insertArticle(article);
            model.addAttribute("message", "文章添加成功");
            return "redirect:toArticleManage.do";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    //声明用于存放留言回复信息的List集合
    private List<Words> wordsList;
    //跳转到查看文章内容页面
    @RequestMapping(value = "/toArticleView.do")
    public String toArticleView(@RequestParam int r_id, Model model) {
        //封装留言信息
        wordsList = service.selectArticleWords();
        model.addAttribute("wordsList", wordsList);

        //查询文章信息
        Article article = service.selectArticleById(r_id);
        System.out.println("查询到当前文章的ID值：" + article.getR_id());
        if (article != null) {
            model.addAttribute("article", article);
            return "view/admin_article/articleView";
        } else {
            return null;
        }
    }



    //保存留言信息
    @RequestMapping(value = "/saveArticleWords")
    public String saveArticleWords(Words words) {
        String r_id = words.getLw_for_article_id();
        service.insertArticleWords(words);
        return "redirect:toArticleView.do?r_id=" + r_id;
    }

    //删除留言信息
    @RequestMapping(value = "/deleteArticleWords")
    public String deleteArticleWords(int lw_id, int r_id) {
        service.deleteArticleWords(lw_id);
        return "redirect:toArticleView.do?r_id=" + r_id;
    }

    //去编辑页面
    @RequestMapping(value = "/toEditPage.do")
    public String toEditPage(Model model, @RequestParam int r_id) {
        //先去查询
        Article article = service.selectArticleById(r_id);
        if (article != null) {
            List<Module> list = service.selectModuleAll();
            model.addAttribute("moduleList", list);
            //模块匹配
            model.addAttribute("article", article);
            return "view/admin_article/articleUpdate";
        } else {
            return null;
        }
    }

    //编辑帖子
    @RequestMapping(value = "/updateArticle")
    public String updateArticle(Article article, Model model, HttpSession session) {
        service.updateArticle(article);
        return "redirect:toArticleManage.do";
    }

    //删除帖子
    @RequestMapping(value = "/deleteArticle.do")
    public String deleteArticle(@RequestParam int r_id, HttpSession session) {
        try {
            service.deleteArticle(r_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findByPage.do";
    }

    @RequestMapping(value = "/updateArticleToNormalById")
    public String updateArticleToNormalById(Article article){
        article.setR_status("2");
        service.updateArticle(article);
        return "redirect:findByPage.do";
    }

    @RequestMapping(value = "/updateArticleToGreatById")
    public String updateArticleToGreatById(Article article){
        article.setR_status("1");
        service.updateArticle(article);
        return "redirect:findByPage.do";
    }

}
