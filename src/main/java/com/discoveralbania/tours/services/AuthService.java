package com.discoveralbania.tours.services;

import com.discoveralbania.tours.dtos.AuthenticationRequestDto;
import com.discoveralbania.tours.dtos.AuthenticationResponseDto;
import com.discoveralbania.tours.exceptions.InvalidPayloadException;
import com.discoveralbania.tours.exceptions.TokenGenerationException;
import com.discoveralbania.tours.models.User;
import com.discoveralbania.tours.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

        public AuthenticationResponseDto loginUser(AuthenticationRequestDto payload) throws InvalidPayloadException, TokenGenerationException {//        boolean isSamePassword = checkUserPassword(payload.getPassword(), user.getPassword());

//        User user = userRepository.findByUsername(payload.getUsername()).orElseThrow(() -> new InvalidPayloadException("User not found"));
 User user = new User("admin", "admin123321");
        boolean isSamePassword = checkUserPassword(payload.getPassword(), user.getPassword());
        if (!isSamePassword) {
            throw new InvalidPayloadException("Wrong password.");
        }
        AuthenticationResponseDto authResponseDto = new ModelMapper().map(user, AuthenticationResponseDto.class);
        try {
            String accessToken = jwtTokenProvider.generateTestAccessToken(user);
            authResponseDto.setJwt(accessToken);
        } catch (Exception e) {
            throw new TokenGenerationException();
        }

        return authResponseDto;
    }

        private boolean checkUserPassword(String plainPassword, String encodedPassword) {
            return true;
//            return passwordEncoder.matches(plainPassword, encodedPassword);
    }
}
