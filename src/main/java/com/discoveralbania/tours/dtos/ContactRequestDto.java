package com.discoveralbania.tours.dtos;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequestDto implements EmailRequest{

    @NotBlank
    private String name;

    @NotBlank
    private String subject;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String message;

    @Override
    public String toEmailSubject() {
        return "New Contact Form Message from " + name;
    }

    @Override
    public String toEmailBody() {
        return "Name: " + name + "\n" +
                "Subject: " + subject + "\n\n" +
                "Email: " + email + "\n\n" +
                "Message:\n" + message;
    }
}
