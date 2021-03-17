package de.microtema.server.vo;

import lombok.Data;

import java.util.UUID;

@Data
public class Person {

    private UUID id;

    private String name;

    private String email;

    private Address address;
}
