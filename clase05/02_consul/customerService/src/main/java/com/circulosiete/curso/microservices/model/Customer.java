package com.circulosiete.curso.microservices.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(updatable = false)
  private String id;

  @Version
  private Long version;

  @Column(name = "created_at", nullable = true)
  @CreatedDate
  //@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
  private Date createdAt;

  @Column(name = "last_modified_at", nullable = true)
  @LastModifiedDate
  //@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
  private Date lastModifiedAt;

  private String name;

  private String email;
}
