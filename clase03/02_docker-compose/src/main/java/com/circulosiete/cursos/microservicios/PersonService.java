package com.circulosiete.cursos.microservicios;

import com.circulosiete.cursos.microservicios.model.Person;
import com.circulosiete.cursos.microservicios.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {
  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person findByEmail(String email) {
    return personRepository.findByEmail(email)
      .orElseThrow(() -> new IllegalStateException("no se encontro a : " + email));
  }

  public Person create(Person person) {
    return personRepository.save(person);
  }

}
