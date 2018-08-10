package ru.bellintegrator.practice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    private static final long serialVersionUID = -123455L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "code")
    private Integer code;

    @Column(name = "name")
    private String name;
/*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id", insertable = false, updatable = false)
    @JsonManagedReference
    private User user;
*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name="country_doc",
            joinColumns=@JoinColumn (name="country_id"),
            inverseJoinColumns=@JoinColumn(name="doc_id"))
    //@JsonBackReference
    @JsonManagedReference
    private List<Doc> docs;

    public Country() {
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

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", code=" + code + ", name='" + name + '\'' + '}';
    }
}