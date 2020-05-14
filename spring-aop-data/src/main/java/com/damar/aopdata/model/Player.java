package com.damar.aopdata.model;

import lombok.Data;

@Data
public class Player {

    private long playerId;

    private String name;

    private String surname;

    private Boolean active;

    private PlayerDetail playerDetail;

}
