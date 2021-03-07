package dev.alexanderbell.ticketmaster.controller;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.service.ArtistRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistRetrievalService artistRetrievalService;

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable("artistId") @Min(value = 0, message = "Artist ID must be positive") Long artistId){
        return ResponseEntity.ok(artistRetrievalService.retrieveArtistWithEvents(artistId));
    }
}
