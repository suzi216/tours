package com.discoveralbania.tours.controllers;

import com.discoveralbania.tours.dtos.TourCreationRequestDto;
import com.discoveralbania.tours.dtos.TourDto;
import com.discoveralbania.tours.dtos.TourUpdateRequestDto;
import com.discoveralbania.tours.exceptions.ResourceNotFoundException;
import com.discoveralbania.tours.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import jakarta.validation.Valid;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tour")
@CrossOrigin(origins = {"http://localhost:3000"})
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TourDto> createTour(@ModelAttribute @Valid TourCreationRequestDto payload){
        try {
            TourDto responseDto = tourService.createTour(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage())).build();
        }
    }
    @PutMapping(value = "/{tourId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TourDto> updateTour(@PathVariable UUID tourId, @ModelAttribute @Valid TourUpdateRequestDto payload){
        try{
            TourDto responseDto = tourService.updateTour(tourId, payload);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage())).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage())).build();
        }
    }

    @DeleteMapping("/{tourId}")
//    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'SCOPE_WORKER')")
    public ResponseEntity<?> deleteTour(@PathVariable UUID tourId) throws ResourceNotFoundException{
        try {
            tourService.deleteUniversity(tourId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage())).build();
        }
    }
}
