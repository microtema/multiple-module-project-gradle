package de.microtema.server.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AddressDTO {

    @NotEmpty
    private String street;

    private String street2;

    @NotNull
    private String city;

    @NotNull
    private Long zip;
}
