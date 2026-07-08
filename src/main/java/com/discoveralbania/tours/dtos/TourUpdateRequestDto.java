package com.discoveralbania.tours.dtos;

import com.discoveralbania.tours.enums.CategoryType;
import com.discoveralbania.tours.enums.Cities;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class TourUpdateRequestDto {
    private String title;
    private String description;
    private Cities city;
    private String duration;
    private Double pricePerPerson;
    private String coverImage;

    private List<String> guideLanguages;
    private List<MultipartFile> gallery;
    private Double discount;

    private List<LocalDate> availableDates;

    private CategoryType category;
    private String tourType;

    private String country;
    private String startPoint, endPoint, pickupInfo, transportationType, guideName, cancellationPolicy;

    private Integer maxGroupSize, minGroupSize;
    private String itinerary;
}
