package com.example.ipandaitems.model.greendao;

import com.example.ipandaitems.model.entry.BaseInfo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by 张豫耀 on 2017/8/23.
 */
@Entity
public class User extends BaseInfo {
    @Id
    private Long id;
    private String img;
    private String name;
    private String time;
    private String url;

    @Generated(hash = 569763639)
    public User(Long id, String img, String name, String time, String url) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.time = time;
        this.url = url;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
