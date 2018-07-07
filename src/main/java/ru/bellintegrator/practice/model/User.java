package ru.bellintegrator.practice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    private static final long serialVersionUID = -123453L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "second_Name")
    private String secondName;
    @Column(name = "middle_Name")
    private String middleName;
    @Column(name = "possition")
    private String possition;
    @Column(name = "doc_Number")
    private Integer docNumber;
    @Column(name = "doc_Date")
    private Date docDate;
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "is_Identified")
    private Boolean isIdentified= true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "office_Id")
    //@JsonManagedReference
    @JsonBackReference
    private Office office;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doc_Id")
    @JsonManagedReference
    private Doc doc;

   // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonBackReference
    //private List<Country> countries;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User() {
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName='" + firstName + '\'' + ", secondName='" + secondName + '\'' + ", middleName='" + middleName + '\'' + ", possition='" + possition + '\'' + ", docNumber=" + docNumber + ", docDate=" + docDate + ", phone=" + phone + ", isIdentified=" + isIdentified + ", doc=" + doc + '}';
    }
}
