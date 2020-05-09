package com.damar.aopdata.model;

import lombok.Data;

@Data
public class User {

    private long userId;

    private String name;

    private Boolean active;

    private Contact contact;

}
