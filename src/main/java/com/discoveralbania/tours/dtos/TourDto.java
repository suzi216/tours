package com.discoveralbania.tours.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TourDto {
    private String title, description, city, duration, category,tourType;
    private Double pricePerPerson, discount;
    private List<String> gallery, guideLanguages, coverImage;

    private List<LocalDate> availableDates;

    private String country;
    private String startPoint, endPoint, pickupInfo, transportationType, guideName, cancellationPolicy;

    private Integer maxGroupSize, minGroupSize;
    private List<ItineraryItem> itinerary;
}
