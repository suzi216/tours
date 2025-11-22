package com.discoveralbania.tours.services;

import com.discoveralbania.tours.dtos.TourCreationRequestDto;
import com.discoveralbania.tours.dtos.TourDto;
import com.discoveralbania.tours.dtos.TourUpdateRequestDto;
import com.discoveralbania.tours.exceptions.ResourceNotFoundException;
import com.discoveralbania.tours.models.Tour;
import com.discoveralbania.tours.repositories.TourRepository;
import com.discoveralbania.tours.utils.FieldUpdater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    @Transactional
    public TourDto updateTour(UUID tourId, TourUpdateRequestDto payload) throws ResourceNotFoundException {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Tour does not exist"));

        FieldUpdater.updateFields(tour, payload, false);
        tour.setId(tourId);
        tour = tourRepository.save(tour);
        return new ModelMapper().map(tour, TourDto.class);
    }

    public void deleteUniversity(UUID tourId) throws ResourceNotFoundException {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Tour does not exist"));
        tour.setDeletedAt(new Date());
        tourRepository.save(tour);

    }

    public TourDto getTour(UUID tourId) throws ResourceNotFoundException {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Tour does not exist"));
        return new ModelMapper().map(tour, TourDto.class);
    }

    public Page<TourDto> getToursInfoForPublic(List<String> cities, Pageable pageable) {
        Page<Tour> tours;
        if (cities == null || cities.isEmpty()) {
            tours = tourRepository.findAll(pageable);
        } else {
            tours = tourRepository.findAllByCityIn(cities, pageable);
        }
        return tours.map(TourDto::buildFrom);
    }
}
