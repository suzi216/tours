package com.discoveralbania.tours.services;

import com.discoveralbania.tours.dtos.TourCreationRequestDto;
import com.discoveralbania.tours.dtos.TourDto;
import com.discoveralbania.tours.models.Tour;
import com.discoveralbania.tours.repositories.TourRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;

//    public TourService(TourRepository tourRepository) {
//        this.tourRepository = tourRepository;
//    }

    @Transactional
    public TourDto createTour(TourCreationRequestDto payload) {
        Tour tour = new ModelMapper().map(payload, Tour.class);
        tour = tourRepository.save(tour);
        return new ModelMapper().map(tour, TourDto.class);
    }
}
