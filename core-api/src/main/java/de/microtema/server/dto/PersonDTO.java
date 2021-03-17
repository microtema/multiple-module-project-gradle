package de.microtema.server.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PersonDTO {

    @NotEmpty
    private String name;

    @NotNull
    private Date dob;

    @NotNull
    private String email;

    private AddressDTO address;
}
