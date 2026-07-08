package com.discoveralbania.tours.services;

import com.discoveralbania.tours.dtos.TourCreationRequestDto;
import com.discoveralbania.tours.dtos.TourDto;
import com.discoveralbania.tours.dtos.TourUpdateRequestDto;
import com.discoveralbania.tours.enums.CategoryType;
import com.discoveralbania.tours.enums.Cities;
import com.discoveralbania.tours.exceptions.ResourceNotFoundException;
import com.discoveralbania.tours.models.Tour;
import com.discoveralbania.tours.repositories.TourRepository;
import com.discoveralbania.tours.utils.FieldUpdater;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.criteria.Predicate;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    @Transactional
    public TourDto createTour(TourCreationRequestDto payload) throws IOException {

        Tour tour = modelMapper.map(payload, Tour.class);
        // Upload gallery
        List<String> galleryUrls = new ArrayList<>();

        if (payload.getGallery() != null) {
            for (MultipartFile image : payload.getGallery()) {
                galleryUrls.add(cloudinaryService.uploadImage(image));
            }
        }

        tour.setGallery(galleryUrls);

        tour.setCreatedAt(new Date());
        tour.setCreatedBy(UUID.randomUUID());
        tour = tourRepository.save(tour);
        return new ModelMapper().map(tour, TourDto.class);
    }
    @Transactional
    public TourDto updateTour(UUID tourId, TourUpdateRequestDto payload) throws ResourceNotFoundException, IOException {

        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Tour does not exist"));
        FieldUpdater.updateFields(tour, payload, false);

        // Upload new gallery images
        if (payload.getGallery() != null && !payload.getGallery().isEmpty()) {

            List<String> galleryUrls = tour.getGallery();

            for (MultipartFile image : payload.getGallery()) {
                galleryUrls.add(cloudinaryService.uploadImage(image));
            }
            tour.setGallery(galleryUrls);
        }
        tour.setUpdatedAt(new Date());
        tour = tourRepository.save(tour);
        return modelMapper.map(tour, TourDto.class);
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

    public Page<TourDto> getToursInfoForPublic(List<Cities> cities, List<CategoryType> categories, Pageable pageable) {

        Specification<Tour> spec = (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (cities != null && !cities.isEmpty()) {

                CriteriaBuilder.In<Cities> inClause = cb.in(root.get("city"));
                for (Cities city : cities) {
                    inClause.value(city);
                }
                predicates.add(inClause);
            }

            if (categories != null && !categories.isEmpty()) {

                CriteriaBuilder.In<CategoryType> inClause = cb.in(root.get("category"));
                for (CategoryType category : categories) {
                    inClause.value(category);
                }
                predicates.add(inClause);
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Tour> page = tourRepository.findAll(spec, pageable);

        return page.map(TourDto::buildFrom);
    }

}
