package com.circulosiete.cursos.microservicios;

import com.circulosiete.cursos.microservicios.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/people")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/email")
  public Person findByEmail(@RequestParam("email") String email) throws Throwable {
    return personService.findByEmail(email);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Person create(@RequestBody Person person) {
    return personService.create(person);
  }
}
