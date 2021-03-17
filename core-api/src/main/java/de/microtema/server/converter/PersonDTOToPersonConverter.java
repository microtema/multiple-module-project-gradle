package de.microtema.server.converter;

import de.microtema.model.converter.Converter;
import de.microtema.server.dto.PersonDTO;
import de.microtema.server.vo.Person;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonDTOToPersonConverter implements Converter<Person, PersonDTO> {

    private final AddressDTOToAddressConverter addressConverter;

    @Override
    public void update(Person dest, PersonDTO orig) {

        dest.setEmail(orig.getEmail());
        dest.setName(orig.getName());

        dest.setAddress(addressConverter.convert(orig.getAddress()));
    }
}
