package com.discoveralbania.tours.repositories;

import com.discoveralbania.tours.models.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TourRepository extends JpaRepository<Tour, UUID>, JpaSpecificationExecutor<Tour> {
    @Query(value = "SELECT tour FROM Tour tour WHERE tour.city in ?1")
    Page<Tour> findAllByCityIn(List<String> cities, Pageable pageable);

}
