package com.discoveralbania.tours.dtos;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String subject;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String message;
}
