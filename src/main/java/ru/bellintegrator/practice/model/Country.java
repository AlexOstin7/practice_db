package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private Integer code;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "countryId")


    private User user;

    public Country() {
    }

    @Override
    public String toString() {
        return "Country{" + "code=" + code + ", name='" + name + '\'' + '}';
    }
}