package ru.bellintegrator.practice.model;

import javax.persistence.*;


@Entity
@Table(name = "RegUser")
public class RegUser {
    private static final long serialVersionUID = -1234567L;
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "active")
    private Boolean active = false ;

    public RegUser() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "RegUser{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + ", code='" + code + '\'' + ", isActive=" + active + '}';
    }
}
