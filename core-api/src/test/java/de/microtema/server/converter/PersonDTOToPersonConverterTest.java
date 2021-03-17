package de.microtema.server.converter;

import de.microtema.model.builder.annotation.Inject;
import de.microtema.model.builder.annotation.Model;
import de.microtema.model.builder.util.FieldInjectionUtil;
import de.microtema.server.dto.PersonDTO;
import de.microtema.server.vo.Address;
import de.microtema.server.vo.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonDTOToPersonConverterTest {

    @InjectMocks
    PersonDTOToPersonConverter sut;

    @Mock
    AddressDTOToAddressConverter addressConverter;

    @Model
    PersonDTO dto;

    @BeforeEach
    void setUp() {
        FieldInjectionUtil.injectFields(this);
    }

    @Test
    void convert(@Mock Address address) {

        // Given
        when(addressConverter.convert(any())).thenReturn(address);

        // When
        Person answer = sut.convert(dto);

        // Then
        assertNotNull(answer);

        assertEquals(dto.getName(), answer.getName());
        assertEquals(dto.getEmail(), answer.getEmail());

        assertEquals(answer.getAddress(), address);

        verify(addressConverter).convert(dto.getAddress());
    }
}
