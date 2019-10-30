package com.circulosiete.cursos.microservicios.model;

import com.domingosuarez.validation.constraints.Constrained;
import com.domingosuarez.validation.constraints.Constraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "persons")
@Constrained
public class Person implements Serializable {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(updatable = false)
  private String id;

  @Version
  private Long version;

  @NotBlank
  private String name;

  @NotBlank
  @Email
  @Length(min = 1, max = 30)
  private String email;

  @Constraint(property = "name")
  public Boolean isValid() {
    return !name.contains("Trump");
  }
}
