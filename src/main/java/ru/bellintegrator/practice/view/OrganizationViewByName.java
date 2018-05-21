package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModelProperty;

/**
 * Dummy data view
 * Just for test
 */
public class OrganizationViewByName {
    @ApiModelProperty(hidden = true)

    public String name;

    public Long inn;

    public Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    //для jackson
    public OrganizationViewByName() {

    }

    public OrganizationViewByName(String name, Long inn, Boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }


    @Override
    public String toString() {
        return "OrganizationView{" + "name='" + name + '\'' +  ", inn=" + inn  + ", isActive=" + isActive + '}';
    }
}