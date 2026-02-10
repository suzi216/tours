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
        return "New Custom Tour Request - " + destination;
    }

    @Override
    public String toEmailBody() {
        return "Custom Tour Request\n\n" +
                "Destination: " + destination + "\n" +
                "Days: " + days + "\n" +
                "People: " + people + "\n" +
                "Budget: " + budget + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone;
    }
}
