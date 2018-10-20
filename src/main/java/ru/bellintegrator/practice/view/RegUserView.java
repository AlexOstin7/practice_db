package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class RegUserView {
    @ApiModelProperty(hidden = true)
    public String login;

    public String password;

    public String name;

    public String code;

    public Boolean active;

    public RegUserView() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "RegUserView{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + ", code='" + code + '\'' + ", isActive=" + active + '}';
    }
}