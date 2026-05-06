package com.discoveralbania.tours.controllers;

import com.discoveralbania.tours.dtos.BookingRequest;
import com.discoveralbania.tours.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = {"http://localhost:3000", "https://toursfrontend.vercel.app","https://www.discover-albania.com/" })
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingRequest request) {
        if (!request.isAgreeTerms()) {
            return ResponseEntity.badRequest().body("You must agree to terms.");
        }
        try {
            bookingService.createBooking(request);
            return ResponseEntity.status(201).body("Booking created successfully!");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to book tour.");
        }
    }

}
