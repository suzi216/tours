package com.discoveralbania.tours.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "contacts")
public class Contact extends AuditEntity{

    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String email;

    private String subject;

    private Integer days;

    private Integer people;

    private Integer budget;

    private String phone;

    @Column(length = 5000)
    private String message;
}
