package ru.bellintegrator.practice.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String firstName;

    public String secondName;

    public String middleName;

    public String possition;

    public Integer phone;

    public Integer docNumber;

    public Date docDate;

    public Boolean isIdentified;

    public Long officeId;

    public Integer docCode;

    public String docName;

    public Integer citizenshipCode;

    public String citizenshipName;

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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Integer docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    @Override
    public String toString() {
        return "UserView{" + "firstName='" + firstName + '\'' + ", secondName='" + secondName + '\'' + ", middleName='" + middleName + '\'' + ", possition='" + possition + '\'' + ", phone=" + phone + ", docNumber=" + docNumber + ", docDate=" + docDate + ", isIdentified=" + isIdentified + ", officeId=" + officeId + ", docCode=" + docCode + ", docName='" + docName + '\'' + ", citizenshipCode=" + citizenshipCode + ", citizenshipName='" + citizenshipName + '\'' + '}';
    }
}