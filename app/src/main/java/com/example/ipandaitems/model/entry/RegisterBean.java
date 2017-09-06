package com.example.ipandaitems.model.entry;

/**
 * Created by 1 on 2017/9/5.
 */

public class RegisterBean {
    private String name;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public RegisterBean(String name, String pwd) {

        this.name = name;
        this.pwd = pwd;
    }
}
