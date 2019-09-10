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
    private String v1;
    private String v2;
    private String v3;
    private String v4;

    @Generated(hash = 1861828227)
    public ProjectDB(Long id, String name, String price, String time,
                     String commission, String remarks, String v1, String v2, String v3,
                     String v4) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.time = time;
        this.commission = commission;
        this.remarks = remarks;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
    }

    @Override
    public String toString() {
        return "ProjectDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", time='" + time + '\'' +
                ", commission='" + commission + '\'' +
                ", remarks='" + remarks + '\'' +
                ", v1='" + v1 + '\'' +
                ", v2='" + v2 + '\'' +
                ", v3='" + v3 + '\'' +
                ", v4='" + v4 + '\'' +
                '}';
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

    public String getV1() {
        return this.v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return this.v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return this.v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public String getV4() {
        return this.v4;
    }

    public void setV4(String v4) {
        this.v4 = v4;
    }


}
