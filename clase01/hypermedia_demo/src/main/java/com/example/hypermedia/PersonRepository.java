package com.example.hypermedia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {
  Page<Person> findAllByNombreIsLike(String name, Pageable page);

  Optional<Person> findByEmail(String email);
  //Person findByEmail(String email);
}
