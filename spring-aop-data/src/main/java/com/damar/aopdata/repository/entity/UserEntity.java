package com.damar.aopdata.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ACTIVE")
    private Boolean active;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ContactEntity contact;
}
