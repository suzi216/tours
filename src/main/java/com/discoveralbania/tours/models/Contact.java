package com.discoveralbania.tours.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    private LocalDate startDate;

    private LocalDate endDate;

    private String phone;

    @Column(length = 5000)
    private String message;

    @PrePersist
    @PreUpdate
    private void calculateDays() {
        if (startDate != null && endDate != null) {
            this.days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        }
    }
}
