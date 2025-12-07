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

}
