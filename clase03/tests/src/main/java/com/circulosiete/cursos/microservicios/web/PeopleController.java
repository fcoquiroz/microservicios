package com.circulosiete.cursos.microservicios.web;

import com.circulosiete.cursos.microservicios.business.PeopleService;
import com.circulosiete.cursos.microservicios.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {
  private final PeopleService peopleService;

  public PeopleController(PeopleService peopleService) {
    this.peopleService = peopleService;
  }

  @GetMapping
  public List<Person> people() {
    return peopleService.findPeople();
  }

  @GetMapping("/{id}")
  public Person findById(
    @PathVariable("id") String id) {
    return peopleService.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Person post(
    @RequestBody Person person) {
    return peopleService.add(person);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(
    @PathVariable("id") String id) {
    peopleService.delete(id);
  }

}
