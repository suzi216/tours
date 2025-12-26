package com.discoveralbania.tours.controllers;

import com.discoveralbania.tours.dtos.ContactRequestDto;
import com.discoveralbania.tours.services.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = {"http://localhost:3000", "https://toursfrontend.vercel.app" })
@RequiredArgsConstructor
public class ContactController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendContactMessage(@Valid @RequestBody ContactRequestDto request) {
        try{
            emailService.sendContactEmail(request);
            return ResponseEntity.ok("Email sent");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Failed to send email.");
        }
    }
}
