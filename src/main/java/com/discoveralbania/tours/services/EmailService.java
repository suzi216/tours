package com.discoveralbania.tours.services;

import com.discoveralbania.tours.dtos.ContactRequestDto;
import com.discoveralbania.tours.models.Contact;
import com.discoveralbania.tours.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final ContactRepository contactRepository;
    private final JavaMailSender mailSender;

    private final ModelMapper modelMapper;
    public void sendContactMessage(ContactRequestDto request) {
        Contact contact = modelMapper.map(request, Contact.class);
        contact.setCreatedAt(new Date());
        contact.setCreatedBy(UUID.randomUUID());
        contactRepository.save(contact);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("suzana.marsela@gmail.com");
        message.setTo("suzana.marsela@gmail.com");
        message.setSubject("New Contact Form Message from " + request.getName());
        message.setText(
                "Name: " + request.getName() + "\n" +
                        "Subject: " + request.getSubject() + "\n\n" +
                        "Email: " + request.getEmail() + "\n\n" +
                        "Message:\n" + request.getMessage()
        );
        mailSender.send(message);
    }
}
