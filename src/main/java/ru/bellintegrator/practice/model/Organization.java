package ru.bellintegrator.practice.model;

import javax.persistence.*;

public class Ogranization {


    @Entity
    @Table(name = "Organization")
    public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "name")
        private String name;
        @Column(name = "COUNTRY")
        private String country;
        @Column(name = "STREET")
        private String street;
        @Column(name = "POST_CODE")
        private String postCode;

        public Address() {
        }

        ;
    }
}