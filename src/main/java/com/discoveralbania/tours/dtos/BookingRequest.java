package com.discoveralbania.tours.dtos;

import lombok.Data;

@Data
public class BookingRequest implements EmailRequest{

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
                "People: " + people + "\n\n" +
                "Base Total: €" + baseTotal + "\n" +
                "Discount: €" + discount + "\n" +
                "Total Amount: €" + totalAmount + "\n\n" +
                "Special Requests: " + specialRequests + "\n" +
                "Accepted Terms: " + (agreeTerms ? "Yes" : "No");
    }
}
