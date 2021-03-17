package de.microtema.server.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Address {

    @NotEmpty
    private String street;

    @NotNull
    private String city;

    @NotNull
    private Long zip;
}
