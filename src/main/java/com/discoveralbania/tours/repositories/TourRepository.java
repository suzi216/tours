package com.discoveralbania.tours.repositories;

import com.discoveralbania.tours.models.Tour;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TourRepository extends CrudRepository<Tour, UUID> {
}
