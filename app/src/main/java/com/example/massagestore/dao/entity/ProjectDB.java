package com.example.massagestore.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 老表 on 2019/8/25.
 */

@Entity
public class ProjectDB {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String price;
    private String time;
    private String commission;  //  提成
    private String remarks;

    @Generated(hash = 978937192)
    public ProjectDB(Long id, String name, String price, String time,
            String commission, String remarks) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.time = time;
        this.commission = commission;
        this.remarks = remarks;
    }
    @Generated(hash = 1234576408)
    public ProjectDB() {
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
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getCommission() {
        return this.commission;
    }
    public void setCommission(String commission) {
        this.commission = commission;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
