package com.circulosiete.curso.microservices.api;

import com.circulosiete.curso.microservices.model.Customer;
import com.circulosiete.curso.microservices.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/customer")
public class CustomerController {

  private final CustomerRepository customerRepository;

  public CustomerController(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @PostMapping
  public HttpEntity addCustomer(@RequestBody Customer customer) {
    System.out.println("Estoy llegando: " + ReflectionToStringBuilder.toString(customer));
    return ResponseEntity.ok(customerRepository.save(customer));
  }
}
