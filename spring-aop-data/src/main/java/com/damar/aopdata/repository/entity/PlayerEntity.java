package com.damar.aopdata.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "T_PLAYER")
@Data
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID")
    private long playerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "ACTIVE")
    private Boolean active;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PLAYER_DETAIL_ID")
    private PlayerDetailEntity playerDetail;
}
