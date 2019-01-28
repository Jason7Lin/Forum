package cn.linjinfu.service;

import cn.linjinfu.pojo.Admin;
import cn.linjinfu.pojo.PageBean;

public interface AdminService {

    Admin login(String a_name);

    void insert(Admin admin);

    Admin findByName(String a_name);

    Admin findUserById(int a_id);

    void updateUserById(Admin admin);

    PageBean<Admin> findByPage(Admin admin, int pageCode, int pageSize);

}
