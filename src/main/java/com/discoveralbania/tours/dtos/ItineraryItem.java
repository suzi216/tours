package com.discoveralbania.tours.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItineraryItem {

    private Integer day;

    @Column(columnDefinition = "TEXT")
    private String description;
}
