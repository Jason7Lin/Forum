package cn.linjinfu.pojo;

import java.io.Serializable;

/**
 * 管理人员信息
 *
 * @author TyCoding
 * @date 18-4-26下午9:14
 */
public class Admin implements Serializable {

    // ID编号
    private int a_id;
    // 密码
    private String a_password;
    // 是否管理员
    private String a_key;
    // 黑名单
    private String a_black;

    // 用户名
    private String a_name;
    // 联系电话
    private String a_telephone;
    // 电子邮箱
    private String a_email;
    // 个人简介
    private String a_sign;
    // 注册日期
    private String a_date;
    // 头像
    private String a_photo;
    // 性别
    private String a_sex;
    // 年龄
    private String a_age;


    public String getA_key() {
        return a_key;
    }

    public void setA_key(String a_key) {
        this.a_key = a_key;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_password() {
        return a_password;
    }

    public void setA_password(String a_password) {
        this.a_password = a_password;
    }

    public String getA_telephone() {
        return a_telephone;
    }

    public void setA_telephone(String a_telephone) {
        this.a_telephone = a_telephone;
    }

    public String getA_date() {
        return a_date;
    }

    public void setA_date(String a_date) {
        this.a_date = a_date;
    }

    public String getA_black() {
        return a_black;
    }

    public void setA_black(String a_black) {
        this.a_black = a_black;
    }

    public String getA_email() {
        return a_email;
    }

    public void setA_email(String a_email) {
        this.a_email = a_email;
    }

    public String getA_sign() {
        return a_sign;
    }

    public void setA_sign(String a_sign) {
        this.a_sign = a_sign;
    }

    public String getA_photo() {
        return a_photo;
    }

    public void setA_photo(String a_photo) {
        this.a_photo = a_photo;
    }

    public String getA_sex() {
        return a_sex;
    }

    public void setA_sex(String a_sex) {
        this.a_sex = a_sex;
    }

    public String getA_age() {
        return a_age;
    }

    public void setA_age(String a_age) {
        this.a_age = a_age;
    }
}
