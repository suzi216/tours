package com.discoveralbania.tours.services;

import com.discoveralbania.tours.dtos.TourCreationRequestDto;
import com.discoveralbania.tours.dtos.TourDto;
import com.discoveralbania.tours.models.Tour;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    public TourDto createTour(TourCreationRequestDto payload) {
        Tour tour = new ModelMapper().map(payload, Tour.class);
        return  null;
    }
}
