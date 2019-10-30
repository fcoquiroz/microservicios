package com.circulosiete.cursos.microservicios.repositories;

import com.circulosiete.cursos.microservicios.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
