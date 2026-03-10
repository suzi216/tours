package com.discoveralbania.tours.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class CustomTourRequestDto implements EmailRequest {
    @NotBlank
    private String destination;

    @NotNull
    private int days;

    @NotNull
    private int people;

    @NotNull
    private int budget;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @Override
    public String toEmailSubject() {
        return "Tour Request:" + destination;
    }

    @Override
    public String toEmailBody() {
        return "A new Tour REQUEST from Tourist. All the information below:- \n\n" +
                "Destination: " + destination + "\n" +
                "Days they want : " + days + "\n" +
                "Total number of tourist: " + people + "\n" +
                "Budget for all: " + budget + "\n" +
                "Email of contact person: " + email + "\n" +
                "Phone of contact person: " + phone;
    }
}
