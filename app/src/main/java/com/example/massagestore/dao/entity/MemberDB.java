package com.example.massagestore.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 创建日期：2019/9/4
 * 创建人：崔斌浩
 * QQ:785248126
 */
@Entity
public class MemberDB {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String phone;
    private String price;   //  卡余额
    private String level;   //  会员级别
    @Generated(hash = 1174396259)
    public MemberDB(Long id, String name, String phone, String price,
            String level) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.price = price;
        this.level = level;
    }
    @Generated(hash = 1431404536)
    public MemberDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getLevel() {
        return this.level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
}
