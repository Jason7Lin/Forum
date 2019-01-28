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
@RequestMapping(value = "/userArticle")
public class UserArticleController {
    @Autowired
    private UserArticleService service;

    @RequestMapping("/findByPage.do")
    public String findByPage(Article article, HttpSession session, HttpServletRequest request, Model model,
                             @RequestParam(value = "pageCode", required = false, defaultValue = "1") int pageCode,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {

        //封装好的PageBean
        PageBean page;

        //应该用源代码那种方法

        //保存识别码key，
        //1=从url参数里取并保存到session，0=取用session里的key
        String key = request.getParameter("key");
        if (key != null) {
            session.setAttribute("key", key);
        } else {
            key = (String) session.getAttribute("key");
        }

        //根据模块的识别码，进行不同的条件查询，跳转到不同的页面
        switch (key) {
            //全部帖子
            case "0":
                //module数据
                List<Module> list = service.selectModuleAll();
                model.addAttribute("moduleList", list);
                //模块匹配
                //article由查询表单而来，再回显至查询表单
                model.addAttribute("article", article);
                //调用findByPage
                page = service.findByPage(article, pageCode, pageSize);
                model.addAttribute("page", page);
                return "view/user_article/articleManage";
            //我的帖子
            case "1":
                //保存author，
                //1=从url参数里取并保存到session，0=取用session里的author
                String author = request.getParameter("author");
                if (author != null) {
                    session.setAttribute("author", author);
                } else {
                    author = (String) session.getAttribute("author");
                }
                //article由author而来
                article.setR_author(author);
                page = service.findByPage(article, pageCode, pageSize);
                model.addAttribute("page", page);
                return "view/user_article/articleManageMe";
            case "2":
                //保存module，
                //1=从url参数里取并保存到session，0=取用session里的module
                String module = request.getParameter("module");
                if (module != null) {
                    session.setAttribute("module", module);
                } else {
                    module = (String) session.getAttribute("module");
                }
                //article由module而来
                article.setR_module(module);
                //回显给页面描述版块
                model.addAttribute("article", article);
                page = service.findByPage(article, pageCode, pageSize);
                model.addAttribute("page", page);
                return "view/user_article/articleManageModule";
            case "3":
                //保存status，
                //1=从url参数里取并保存到session，0=取用session里的status
                String status = request.getParameter("status");
                if (status != null) {
                    session.setAttribute("status", status);
                } else {
                    status = (String) session.getAttribute("status");
                }

                //article由status1而来
                article.setR_status(status);
                page = service.findByPage(article, pageCode, pageSize);
                model.addAttribute("page", page);
                return "view/user_article/articleManageGreat";
            default:
                return null;
        }
    }

    //去分页查询页
    @RequestMapping(value = "/toFindByPage.do")
    public String toFindByPage() {
        return "redirect:findByPage.do";
    }

    @RequestMapping(value = "/toArticleWrite.do")
    public String toArticleWrite(Model model) {
        List<Module> list = service.selectModuleAll();
        model.addAttribute("moduleList", list);
        return "view/user_article/articleWrite";
    }

    @RequestMapping(value = "/toArticleManageMe.do")
    public String toArticleManageMe(HttpSession session) {
        String name = (String) session.getAttribute("name");
        return "redirect:findByPage.do?author=" + name + "&key=1";
    }

    @RequestMapping(value = "/insertArticle.do")
    public String insertArticle(Article article, Model model, HttpSession session) {
        try {
            service.insertArticle(article);
            model.addAttribute("message", "文章添加成功");
            return "redirect:toArticleManageMe.do";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/deleteArticle.do")
    public String deleteArticle(@RequestParam int r_id, HttpSession session) {
        service.deleteArticle(r_id);
        return "redirect:toArticleManageMe.do";
    }

    @RequestMapping(value = "/toEditPage.do")
    public String toEditPage(Model model, @RequestParam int r_id) {
        //先去查询
        Article article = service.selectArticleById(r_id);
        if (article != null) {
            List<Module> list = service.selectModuleAll();
            model.addAttribute("moduleList", list);
            //模块匹配
            model.addAttribute("article", article);
            return "view/user_article/articleUpdate";
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/updateArticle")
    public String updateArticle(Article article, Model model, HttpSession session) {
        service.updateArticle(article);
        return "redirect:toArticleManageMe.do";
    }

    /**
     * 跳转到查看文章内容页面
     */
    //声明用于存放留言回复信息的List集合
    private List<Words> wordsList;

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
            return "view/user_article/articleView";
        } else {
            return null;
        }
    }

    /**
     * 保存留言信息
     */
    @RequestMapping(value = "/saveArticleWords")
    public String saveArticleWords(Words words) {
        if (words != null) {
            String r_id = words.getLw_for_article_id();
            service.insertArticleWords(words);
            return "redirect:toArticleView.do?r_id=" + r_id;
        } else {
            return null;
        }
    }


    @RequestMapping(value = "/deleteArticleWords")
    public String deleteArticleWords(int lw_id, int r_id) {
        service.deleteArticleWords(lw_id);
        return "redirect:toArticleView.do?r_id=" + r_id;
    }

}
