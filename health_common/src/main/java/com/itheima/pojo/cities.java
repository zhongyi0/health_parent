package com.itheima.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class cities implements Serializable {
    private int id;
    private String cityid;
    private String city;
    private String provinceid;
    private List<areas> children = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public List<areas> getChildren() {
        return children;
    }

    public void setChildren(List<areas> children) {
        this.children = children;
    }
}
