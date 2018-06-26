package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFilterView {
    @ApiModelProperty(hidden = true)

    public String id;

    public String firstName;

    public String secondName;

    public String middleName;

    public String possition;

    public Long officeId;

    public Integer docCode;

    public Integer citizenShipCode;

    public UserFilterView() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPossition() {
        return possition;
    }

    public void setPossition(String possition) {
        this.possition = possition;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public Integer getCitizenShipCode() {
        return citizenShipCode;
    }

    public void setCitizenShipCode(Integer citizenShipCode) {
        this.citizenShipCode = citizenShipCode;
    }

    @Override
    public String toString() {
        return "UserFilterView{" + "firstName='" + firstName + '\'' + ", secondName='" + secondName + '\'' + ", middleName='" + middleName + '\'' + ", possition='" + possition + '\'' + ", officeId=" + officeId + ", docCode=" + docCode + ", citizenShipCode=" + citizenShipCode + '}';
    }
}