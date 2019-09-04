package com.example.massagestore.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 老表 on 2019/8/31.
 */
@Entity
public class UserDB {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String phone;
    @Generated(hash = 247474434)
    public UserDB(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    @Generated(hash = 1312299826)
    public UserDB() {
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
}
