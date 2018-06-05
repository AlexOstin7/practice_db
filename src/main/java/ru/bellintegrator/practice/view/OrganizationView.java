package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;

public class OrganizationView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String name;

    public String fullName;

    public Long inn;

    public Integer kpp;

    public String address;

    public Integer phone;

    public Boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Integer getKpp() {
        return kpp;
    }

    public void setKpp(Integer kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public OrganizationView() {

    }

    public OrganizationView(String name, Long inn, Boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }

    public OrganizationView(String name, String fullName, Long inn, Integer kpp, String address, Integer phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public OrganizationView(String id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "OrganizationView{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", fullName='" + fullName + '\'' + ", inn=" + inn + ", kpp=" + kpp + ", address='" + address + '\'' + ", phone=" + phone + ", isActive=" + isActive +'}';
    }
}