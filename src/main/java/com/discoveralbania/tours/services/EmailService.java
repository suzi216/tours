package com.discoveralbania.tours.services;

import com.discoveralbania.tours.dtos.BrevoEmailRequest;
import com.discoveralbania.tours.dtos.ContactRequestDto;
import com.discoveralbania.tours.dtos.CustomTourRequestDto;
import com.discoveralbania.tours.dtos.EmailRequest;
import com.discoveralbania.tours.models.Contact;
import com.discoveralbania.tours.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final ContactRepository contactRepository;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final Environment environment;

    private String getBrevoApiKey() {
        String apiKey = environment.getProperty("spring.brevo.api-key");
        return Objects.requireNonNull(apiKey, "Brevo API key must be set in application.yml or environment variable");
    }

    private String getBrevoUrl() {
        String url = environment.getProperty("spring.brevo.url");
        return Objects.requireNonNull(url, "Brevo URL must be set in application.yml");
    }

    public void submitContactForm(ContactRequestDto requests) {
        Contact contact = modelMapper.map(requests, Contact.class);
        contact.setCreatedAt(new Date());
        contact.setCreatedBy(UUID.randomUUID());
        contactRepository.save(contact);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", getBrevoApiKey());

        HttpEntity<BrevoEmailRequest> request =
                getEmailRequestHttpEntity(requests, headers);

        restTemplate.postForEntity(getBrevoUrl(), request, String.class);
    }

    public void sendCustomTourRequest(CustomTourRequestDto requests) {
        Contact contact = modelMapper.map(requests, Contact.class);
        contact.setCreatedAt(new Date());
        contact.setCreatedBy(UUID.randomUUID());
        contactRepository.save(contact);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", getBrevoApiKey());

        HttpEntity<BrevoEmailRequest> request =
                getEmailRequestHttpEntity(requests, headers);

        restTemplate.postForEntity(getBrevoUrl(), request, String.class);
    }

    private static HttpEntity<BrevoEmailRequest> getEmailRequestHttpEntity(
            EmailRequest requests,
            HttpHeaders headers
    ) {
        BrevoEmailRequest payload = new BrevoEmailRequest(
                new BrevoEmailRequest.Sender(
                        "suzana.marsela@gmail.com",
                        "Website Contact"
                ),
                List.of(new BrevoEmailRequest.To("suzana.marsela@gmail.com")),
                requests.toEmailSubject(),
                requests.toEmailBody()
        );

        return new HttpEntity<>(payload, headers);
    }



}
