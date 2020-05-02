package com.damar.aopdata.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_CONTACT")
@Data
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_ID")
    private long contactId;

    @Column(name = "EMAIL_ADDRESS")
    private String email;

    @Column(name = "AGE")
    private Integer age;

}
