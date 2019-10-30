package com.circulosiete.cursos.microservicios.repository;

import com.circulosiete.cursos.microservicios.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {
  Optional<Person> findByEmail(@Param("email") String email);

  Page<Person> findByName(@Param("name") String name, Pageable page) ;
}
