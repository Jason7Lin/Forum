package cn.linjinfu.service.impl;

import cn.linjinfu.mapper.AdminMapper;
import cn.linjinfu.pojo.Admin;
import cn.linjinfu.pojo.PageBean;
import cn.linjinfu.service.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author TyCoding
 * @date 18-4-27上午7:09
 */
@Service
public class AdminServiceImpl implements AdminService {

    /**
     * 注入service层
     */
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 登录的功能
     *
     * @param a_name 输入的用户名
     * @return 返回查询到的该用户名对应的密码
     */
    public Admin login(String a_name) {
        return adminMapper.login(a_name);
    }

    /**
     * 注册功能
     *
     * @param admin 注册的信息
     * @return 返回影响的行数
     */
    public void insert(Admin admin) {
        adminMapper.insert(admin);
    }

    /**
     * 根据用户名查询
     *
     * @param a_name 用户名
     * @return 返回影响的行数
     */
    public Admin findByName(String a_name) {
        return adminMapper.findByName(a_name);
    }

    public Admin findUserById(int a_id){
        return adminMapper.findUserById(a_id);
    }

    public void updateUserById(Admin admin){
        adminMapper.updateUserById(admin);
    }

    public PageBean<Admin> findByPage(Admin admin, int pageCode, int pageSize) {
        //使用倒序
        String orderBy = "a_id" + " desc";
        //使用Mybatis分页插件
        PageHelper.startPage(pageCode, pageSize, orderBy);

        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        //PageHelper插件的page类
        Page<Admin> page = adminMapper.findByPage(admin);

        //返回封装好的PageBean
        return new PageBean<Admin>(pageCode, (int) Math.ceil((double) (page.getTotal() / (double) pageSize)), (int) page.getTotal(), pageSize, page.getResult());
    }

}
