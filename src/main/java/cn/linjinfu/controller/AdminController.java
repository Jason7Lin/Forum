package cn.linjinfu.controller;

import cn.linjinfu.pojo.Admin;
import cn.linjinfu.pojo.Article;
import cn.linjinfu.pojo.PageBean;
import cn.linjinfu.service.AdminService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理人员的Controller层
 *
 * @author TyCoding
 * @date 18-4-27上午7:05
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    /**
     * 注入service
     */
    @Autowired
    private AdminService adminService;


    /**
     * 跳转到系统登录首页
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    /**
     * 登录功能
     */
    @RequestMapping(value = "/login")
    public String login(@RequestParam String a_name, @RequestParam String a_password, @RequestParam String a_key, Model model, HttpSession session) {
        Admin admin = adminService.login(a_name);
        if (admin != null) {
            if (admin.getA_password().equals(a_password) && admin.getA_key().equals(a_key)) {
                session.setAttribute("name", admin.getA_name());
                //是管理员
                if (admin.getA_key().equals("1")) {
                    return "view/admin_page";
                }
                //是用户
                if (admin.getA_key().equals("0")) {
                    //是白名单
                    if (admin.getA_black().equals("0")) {
                        return "view/user_page";
                    }
                    //是黑名单
                    if (admin.getA_black().equals("1")) {
                        model.addAttribute("message", "您的账号已被禁用，请联系管理员");
                        return "view/login/info";
                    }
                }
                //异常
                return null;
            } else {
                model.addAttribute("message", "用户名或密码错误");
                return "view/login/info";
            }
        } else {
            model.addAttribute("message", "登录失败");
            return "view/login/info";
        }
    }

    /**
     * 注册功能
     */
    @RequestMapping(value = "/register")
    public String register(Admin admin, HttpSession session) {
        adminService.insert(admin);
        session.setAttribute("name", admin.getA_name());
        if (admin.getA_key().equals("1")) {
            return "view/admin_page";
        }
        if (admin.getA_key().equals("0")) {
            return "view/user_page";
        }
        return null;
    }

    /**
     * 根据用户名查询
     */
    @ResponseBody
    @RequestMapping(value = "/findByName")
    public String findByName(@RequestBody Admin admin) {
        Admin info = adminService.findByName(admin.getA_name());
        System.out.println(JSONObject.toJSONString(info));
        return JSONObject.toJSONString(info);
    }


    /**
     * 退出登录的功能
     */
    @RequestMapping(value = "/outLogin")
    public String outLogin(HttpSession session) {
        session.invalidate();
        return "index";
    }


    /**
     * 跳转到userPage首页面
     */
    @RequestMapping(value = "/userPage")
    public String userPage() {
        return "view/user_page";
    }

    /**
     * 跳转到adminPage首页面
     */
    @RequestMapping(value = "/adminPage")
    public String adminPage() {
        return "view/admin_page";
    }

    @RequestMapping(value = "/toUserManage")
    public String toUserManage(){
        return "redirect:findByPage.do";
    }

    @RequestMapping(value = "/findByPage.do")
    public String findByPage(Admin user, HttpSession session, HttpServletRequest request, Model model,
                             @RequestParam(value = "pageCode", required = false, defaultValue = "1") int pageCode,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {

        //回显
        model.addAttribute("user", user);
        //调用findByPage
        PageBean page = adminService.findByPage(user, pageCode, pageSize);
        model.addAttribute("page", page);
        return "view/admin/adminManage";
    }

    @RequestMapping(value = "/updateUserToWhiteById")
    public String updateUserBlackById(Admin admin){
        admin.setA_black("0");
        adminService.updateUserById(admin);
        return "redirect:findByPage.do";
    }

    @RequestMapping(value = "/updateUserToBlackById")
    public String updateUserToBlackById(Admin admin){
        admin.setA_black("1");
        adminService.updateUserById(admin);
        return "redirect:findByPage.do";
    }

    @RequestMapping(value = "/toFindOtherUserInfo")
    public String toFindOtherUserInfo(Admin admin){
        String a_name = admin.getA_name();
        return "redirect:findOtherUserInfo.do?a_name="+a_name;
    }

    //以下个人中心内容
    @RequestMapping(value = "/findUserInfo.do")
    public String findUserInfo(String a_name, Model model) {
        Admin user = adminService.findByName(a_name);
        model.addAttribute("user", user);
        return "view/info/myinfo";
    }

    @RequestMapping(value = "/findOtherUserInfo.do")
    public String findOtherUserInfo(String a_name, Model model) {
        Admin user = adminService.findByName(a_name);
        model.addAttribute("user", user);
        return "view/info/otherinfo";
    }

    //根据id查询用户信息方法
    @ResponseBody//使其不走视图解析器 ajax...
    @RequestMapping(value = "/findUserById")
    public Admin findById(@RequestBody Admin admin) {
        Admin user = adminService.findUserById(admin.getA_id());
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    //更新客户信息的方法
    @RequestMapping(value = "/updateUserById")
    public String updateUserById(Admin admin) {
        adminService.updateUserById(admin);
        String name = admin.getA_name();
        return "redirect:findUserInfo.do?a_name=" + name;
    }

}
