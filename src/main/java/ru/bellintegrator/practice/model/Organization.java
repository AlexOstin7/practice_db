package ru.bellintegrator.practice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "full_Name")
    private String fullName;
    @Column(name = "inn")
    private Long inn;
    @Column(name = "kpp")
    private Integer kpp;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "is_Active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonBackReference
    private List<Office> offices;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public int getKpp() {
        return kpp;
    }

    public void setKpp(int kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return offices;
    }

    public void setOffice(List<Office> offices) {
        this.offices = offices;
    }

    public void addOffice(Office office) {
        offices.add(office);
        office.setOrganization(this);
    }

    public void removeOffice(Office office) {
        offices.remove(office);
        office.setOrganization(null);
    }
    public Organization() {
    }

    public Organization(String name, Long inn) {
        this.name = name;
        this.inn = inn;
    }

    public Organization(String name, String fullName, Long inn, Integer kpp, String address, Integer phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Organization(String name, Long inn, Boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }


}