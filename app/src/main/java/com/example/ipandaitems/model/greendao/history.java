package com.example.ipandaitems.model.greendao;

import com.example.ipandaitems.model.entry.BaseInfo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 张豫耀 on 2017/9/5.
 */
@Entity
public class history extends BaseInfo {
    @Id
    private Long id;
    private String img;
    private String url;
    private String time;
    private String name;

    @Generated(hash = 1281784508)
    public history(Long id, String img, String url, String time, String name) {
        this.id = id;
        this.img = img;
        this.url = url;
        this.time = time;
        this.name = name;
    }

    @Generated(hash = 1897821470)
    public history() {
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
