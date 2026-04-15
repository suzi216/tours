package com.discoveralbania.tours.models;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends  AuditEntity{


    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String fullName;
    private String email;
    private String phone;
    private String address;

    private int people;

    private Double baseTotal;
    private Double discount;
    private Double totalAmount;

    private String cardName;
    private String cardLast4;
    private String paymentStatus;

    private String tourId;

    @Column(length = 1000)
    private String specialRequests;

    private String promoCode;

    private Boolean agreeTerms;

}