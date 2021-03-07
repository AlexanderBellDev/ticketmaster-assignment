package dev.alexanderbell.ticketmaster.controller;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.ArtistDataTransformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistDataTransformationService artistDataTransformationService;

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable("artistId") @Min(value = 0, message = "Artist ID must be positive") Long artistId){
        return ResponseEntity.ok(artistDataTransformationService.retrieveArtistWithEvents(artistId));
    }
}
