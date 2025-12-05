package com.discoveralbania.tours.models;

import com.discoveralbania.tours.dtos.ItineraryItem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "tours", indexes = {
        @Index(name = "idx_created_at", columnList = "created_at")
})
@Where(clause = "deleted_at IS NULL")
public class Tour extends AuditEntity{

    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    // ---------- Basic Info ----------
    private String title;


    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;
    private String tourType;

    // ---------- Location ----------
    private String country;
    private String city;

    @Column(name = "start_point")
    private String startPoint;

    @Column(name = "end_point")
    private String endPoint;

    // ---------- Schedule ----------
    private String duration;

    @ElementCollection
    @CollectionTable(name = "tour_available_dates")
    @Column(name = "date")
    private List<LocalDate> availableDates;

    // ---------- Pricing ----------
    private Double pricePerPerson;
    private Double discount;

    // ---------- Capacity ----------
    private Integer maxGroupSize;
    private Integer minGroupSize;

    // ---------- Media ----------
    @Column(name = "cover_image")
    private String coverImage;

    private boolean  isPopular;

    @ElementCollection
    @CollectionTable(name = "tour_gallery")
    @Column(name = "images")
    private List<String> gallery;

    // ---------- Itinerary ----------
    @ElementCollection
    @CollectionTable(name = "tour_itinerary")
    private List<ItineraryItem> itinerary;

    // ---------- Logistics ----------
    @Column(name = "pickup_info", columnDefinition = "TEXT")
    private String pickupInfo;

    @Column(name = "dropoff_info", columnDefinition = "TEXT")
    private String dropoffInfo;

    private String transportationType;

    // ---------- Guide Info ----------
    private String guideName;

    @ElementCollection
    @CollectionTable(name = "tour_guide_languages")
    @Column(name = "language")
    private List<String> guideLanguages;

    // ---------- Policies ----------
    @Column(name = "cancellation_policy", columnDefinition = "TEXT")
    private String cancellationPolicy;

}
