package ru.bellintegrator.practice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private List<Doc> docs;

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "Country{" + "id=" + id + ", code=" + code + ", name='" + name + '\'' + ", docs=" + docs + '}';
    }
}