package com.circulosiete.cursos.microservicios.services;

import com.circulosiete.cursos.microservicios.model.Person;
import com.circulosiete.cursos.microservicios.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {
  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person readPerson(Long id) {
    return personRepository.getOne(id);
  }

}
