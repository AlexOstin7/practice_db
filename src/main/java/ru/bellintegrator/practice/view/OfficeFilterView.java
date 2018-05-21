package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeFilterView {
    @ApiModelProperty(hidden = true)

    public String name;

    public Integer phone;

    public Boolean isActive;

    public Long orgId;

    public OfficeFilterView() {
    }

    public String getName() {
        return name;
    }

    public Integer getPhone() {
        return phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "OfficeFilterView{" + "name='" + name + '\'' + ", phone=" + phone + ", isActive=" + isActive + ", orgId=" + orgId + '}';
    }
}