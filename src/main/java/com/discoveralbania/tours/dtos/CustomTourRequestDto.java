package com.discoveralbania.tours.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data

public class CustomTourRequestDto implements EmailRequest {
    @NotBlank
    private String destination;


    @NotNull
    private int people;

    @NotNull
    private int budget;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

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
        return "A new Tour REQUEST from Tourist. All the information below: \n\n" +
                "Destination: " + destination + "\n" +
                "Preferred Dates for Trip : " + startDate + "-" + endDate + "\n" +
                "Total number of tourist: " + people + "\n" +
                "Budget for all €: " + budget + "\n" +
                "Email of contact person: " + email + "\n" +
                "Phone of contact person: " + phone;
    }
}
