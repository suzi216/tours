package com.discoveralbania.tours.dtos;

import lombok.Data;

@Data
public class BookingRequest {

    private String fullName;
    private String email;
    private String phone;
    private String address;
    private int people;

    private double baseTotal;
    private double discount;
    private double totalAmount;

    private String cardName;
    private String cardNumber;
    private String cardExpiry;
    private String cardCvc;

    private String promoCode;
    private String specialRequests;

    private boolean agreeTerms;
    private String tourId;
}
