package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Countries")
public class Countries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private int code;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "countryId")


    private User user;

    public Countries() {
    }

    @Override
    public String toString() {
        return "Countries{" + "code=" + code + ", name='" + name + '\'' + '}';
    }
}