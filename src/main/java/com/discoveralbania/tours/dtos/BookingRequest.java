package com.discoveralbania.tours.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest implements EmailRequest{

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;
    private String address;

    @NotNull
    private int people;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private double baseTotal;

    private double discount;

    @NotNull
    private double totalAmount;

    private String cardName;
    private String cardNumber;
    private String cardExpiry;
    private String cardCvc;

    private String promoCode;
    private String specialRequests;

    @NotNull
    private boolean agreeTerms;

    @NotBlank
    private String tourId;
    private String tourTitle;

    @Override
    public String toEmailSubject() {
        return "Booking Tour:" + tourTitle;
    }

    @Override
    public String toEmailBody() {
        return "A tour has been booked. Details below:\n\n" +
                "Tour: " + tourTitle + "\n" +
                "Full Name: " + fullName + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "Address: " + address + "\n" +
                "Start and End Date" + startDate + " until" + endDate + "\n" +
                "People: " + people + "\n\n" +
                "Base Total: €" + baseTotal + "\n" +
                "Discount: €" + discount + "\n" +
                "Total Amount: €" + totalAmount + "\n\n" +
                "Special Requests: " + specialRequests + "\n" +
                "Accepted Terms: " + (agreeTerms ? "Yes" : "No");
    }
}
