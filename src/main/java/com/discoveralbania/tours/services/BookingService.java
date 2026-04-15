package com.discoveralbania.tours.services;

import com.discoveralbania.tours.dtos.BookingRequest;
import com.discoveralbania.tours.models.Booking;
import com.discoveralbania.tours.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    public void createBooking(BookingRequest request) {
        Booking booking = modelMapper.map(request, Booking.class);
        booking.setCreatedAt(new Date());
        booking.setCreatedBy(UUID.randomUUID());
        bookingRepository.save(booking);
    }
}
