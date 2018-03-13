package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModelProperty;

/**
 * Dummy data view
 * Just for test
 */
public class OrganizationView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String name;

    public Long inn;

    //для jackson
    public OrganizationView() {

    }

    public OrganizationView(String name, Long inn) {
        this.name = name;
        this.inn = inn;
    }

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";inn" + inn + "}";
    }
}