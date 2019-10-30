package com.circulosiete.curso.microservices.clientes.modelo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Cliente, String> {
  Optional<Cliente> findById(String id);

  Optional<Cliente> findByEmail(String email);

  Optional<Cliente> findByTaxId(String taxId);

  Page<Cliente> findClientesByFirstName(String firstName, Pageable page);

  Page<Cliente> findClientesByLastName(String lastName, Pageable page);

  Page<Cliente> findClientesByFirstNameAndLastName(String firstName, String lastName, Pageable page);

}
