package com.discoveralbania.tours.dtos;

import com.discoveralbania.tours.enums.CategoryType;
import com.discoveralbania.tours.models.Tour;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class TourDto {
    private UUID id;
    private String title, description, city, duration, tourType, coverImage;
    private Double pricePerPerson, discount;
    private List<String> gallery, guideLanguages;
    private CategoryType category;

    private List<LocalDate> availableDates;

    private String country;
    private String startPoint, endPoint, pickupInfo, transportationType, guideName, cancellationPolicy;

    private Integer maxGroupSize, minGroupSize;
    private List<ItineraryItem> itinerary;

    public static TourDto buildFrom(Tour tour) {
        return new ModelMapper().map(tour, TourDto.class);
    }
}
