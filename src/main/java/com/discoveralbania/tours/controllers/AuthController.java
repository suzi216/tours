package com.discoveralbania.tours.controllers;

import com.discoveralbania.tours.dtos.AuthenticationRequestDto;
import com.discoveralbania.tours.dtos.AuthenticationResponseDto;
import com.discoveralbania.tours.exceptions.InvalidPayloadException;
import com.discoveralbania.tours.exceptions.TokenGenerationException;
import com.discoveralbania.tours.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000", "https://toursfrontend.vercel.app" })
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value="/login")
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(@RequestBody AuthenticationRequestDto request) throws InvalidPayloadException, TokenGenerationException, TokenGenerationException, InvalidPayloadException {

        AuthenticationResponseDto token= authService.loginUser(request);

        return ResponseEntity.ok((token));
    }
}