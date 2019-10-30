package com.circulosiete.curso.microservices.repositories;

import com.circulosiete.curso.microservices.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
