package org.openmore.dto.service;

import org.openmore.dto.dto.api.PersonDto;

public interface PersonService {

    Person getById();

    /**增*/
    void create(Person Person);
    /**删*/
    void deleteById(Long id);
    /**改*/
    void update(Long id, Person Person);
    /**查*/
    PersonDto getPersonProfile(long uid);
}