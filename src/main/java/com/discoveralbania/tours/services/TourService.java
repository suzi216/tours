package com.discoveralbania.tours.services;

import com.discoveralbania.tours.dtos.TourCreationRequestDto;
import com.discoveralbania.tours.dtos.TourDto;
import com.discoveralbania.tours.models.Tour;
import com.discoveralbania.tours.repositories.TourRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;
    private final ModelMapper modelMapper;
    @Transactional
    public TourDto createTour(TourCreationRequestDto payload) {

        Tour tour = modelMapper.map(payload, Tour.class);
        tour.setCreatedAt(new Date());
        tour.setCreatedBy(UUID.randomUUID());
        tour = tourRepository.save(tour);
        return new ModelMapper().map(tour, TourDto.class);
    }
}
