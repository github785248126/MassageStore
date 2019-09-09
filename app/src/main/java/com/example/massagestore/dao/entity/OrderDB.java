package com.example.massagestore.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 老表 on 2019/9/9.
 */
@Entity
public class OrderDB {
    @Id(autoincrement = true)
    private Long id;
    private String orderid;
    private String project;
    private String member;
    private String user;
    private String yf_price;
    private String sf_price;
    @Generated(hash = 54877129)
    public OrderDB(Long id, String orderid, String project, String member,
            String user, String yf_price, String sf_price) {
        this.id = id;
        this.orderid = orderid;
        this.project = project;
        this.member = member;
        this.user = user;
        this.yf_price = yf_price;
        this.sf_price = sf_price;
    }
    @Generated(hash = 1609986019)
    public OrderDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderid() {
        return this.orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    public String getProject() {
        return this.project;
    }
    public void setProject(String project) {
        this.project = project;
    }
    public String getMember() {
        return this.member;
    }
    public void setMember(String member) {
        this.member = member;
    }
    public String getUser() {
        return this.user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getYf_price() {
        return this.yf_price;
    }
    public void setYf_price(String yf_price) {
        this.yf_price = yf_price;
    }
    public String getSf_price() {
        return this.sf_price;
    }
    public void setSf_price(String sf_price) {
        this.sf_price = sf_price;
    }
}
