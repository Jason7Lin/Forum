package cn.linjinfu.mapper;

import cn.linjinfu.pojo.Admin;

public interface AdminMapper {
    
    Admin login(String a_name);

    void insert(Admin admin);

    Admin findByName(String a_name);

    Admin findUserById(int a_id);

    void updateUserById(Admin admin);
}
