package cn.linjinfu.pojo;

import java.io.Serializable;

public class Module implements Serializable {
    private Integer m_id;
    private String m_module;

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public String getM_module() {
        return m_module;
    }

    public void setM_module(String m_module) {
        this.m_module = m_module;
    }
}
