package com.ny.lg.api.pojo;


import com.ny.lg.common.utils.ValidatedUtils;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String id;

    @Pattern(regexp = ".{1,32}", message = "用户名长度必须为1~5位", groups = {ValidatedUtils.add.class, ValidatedUtils.update.class})
    private String username;

    private String password;

    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
