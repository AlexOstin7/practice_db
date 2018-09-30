package ru.bellintegrator.practice.model;

import javax.persistence.*;


@Entity
@Table(name = "regUser")
public class RegUser {
    private static final long serialVersionUID = -123456L;
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

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

    @Override
    public String toString() {
        return "RegUser{" + "login='" + login + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + '}';
    }
}
