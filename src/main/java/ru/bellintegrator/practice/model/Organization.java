package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "full_Name")
    private String country;
    @Column(name = "inn")
    private Long inn;
    @Column(name = "kpp")
    private Long kpp;
    @Column(name = "phone")
    private int phone;
    @Column(name = "is_Active")
    private Boolean isActive = true;

    public Organization() {
    }

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Office> office;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
        this.kpp = kpp;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Office> getOffice() {
        return office;
    }

    public void setOffice(List<Office> office) {
        this.office = office;
    }

    public Organization(String name, Long inn) {
        this.name = name;
        this.inn = inn;
    }

    @Override
    public String toString() {
        return "Organization{" + "id=" + id + ", name='" + name + '\'' + ", country='" + country + '\'' + ", inn=" + inn + ", kpp=" + kpp + ", phone=" + phone + ", isActive=" + isActive + '}';
    }
}