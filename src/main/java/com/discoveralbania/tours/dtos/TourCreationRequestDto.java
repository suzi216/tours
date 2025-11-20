package com.discoveralbania.tours.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TourCreationRequestDto {
    @NotEmpty(message = "Tour title is required")
    private String title;
    @NotEmpty(message = "Tour description is required")
    private String description;
    @NotEmpty(message = "City is required")
    private String city;
    @NotEmpty(message = "Duration is required")
    private String duration;
    @NotNull
    @Positive
    private Double pricePerPerson;
    @NotEmpty(message = "Cover image is required")
    private String coverImage;

    private List<String> gallery, guideLanguages;

    private Double discount;

    private List<LocalDate> availableDates;

    private String category;
    private String tourType;

    private String country;
    private String startPoint, endPoint, pickupInfo, transportationType, guideName, cancellationPolicy;

    private Integer maxGroupSize, minGroupSize;
    private List<ItineraryItem> itinerary;

}
