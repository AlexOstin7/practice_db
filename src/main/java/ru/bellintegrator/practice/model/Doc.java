package ru.bellintegrator.practice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doc")
public class Doc {
    private static final long serialVersionUID = -123454L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code")
    private Integer code;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "doc", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="country_doc",
            joinColumns=@JoinColumn(name="doc_id"),
            inverseJoinColumns=@JoinColumn(name="country_id"))
    //@JsonManagedReference
    @JsonBackReference
    private List<Country> countries;

    public Doc() {
    }

    public Doc(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Doc(Integer code, String name, List<Country> countries) {
        this.code = code;
        this.name = name;
        this.countries = countries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void addUser(User user) {
        getUsers().add(user);
        //car.setOwner(this);
        user.setDoc(this);
    }
    public void removeUser(User user) {
        getUsers().remove(user);
       // car.setOwner(null);
        user.setDoc(null);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Doc{" + "id=" + id + ", code=" + code + ", name='" + name + '\'' + ", countries=" + countries + '}';
    }
}
