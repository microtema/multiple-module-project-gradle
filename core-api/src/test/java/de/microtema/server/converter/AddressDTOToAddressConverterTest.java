package de.microtema.server.converter;

import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.util.FieldInjectionUtil;
import de.microtema.server.dto.AddressDTO;
import de.microtema.server.vo.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDTOToAddressConverterTest {

    @Inject
    AddressDTOToAddressConverter sut;

    @Model
    AddressDTO dto;

    @BeforeEach
    void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    void convert() {

        Address answer = sut.convert(dto);

        assertNotNull(answer);
        assertEquals(dto.getCity(), answer.getCity());
        assertEquals(dto.getZip(), answer.getZip());
        assertEquals(dto.getStreet(), answer.getStreet());
    }
}
