package com.itheima.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class provinces implements Serializable {
    private String id;
    private String province;
    private String provinceid;
    private List<cities> children = new ArrayList<>();//子菜单集合

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public List<cities> getChildren() {
        return children;
    }

    public void setChildren(List<cities> children) {
        this.children = children;
    }
}
