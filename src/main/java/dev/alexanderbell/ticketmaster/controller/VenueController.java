package dev.alexanderbell.ticketmaster.controller;

import dev.alexanderbell.ticketmaster.model.Artist;
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
public class VenueController {
    @GetMapping("/venue/{venueId}")
    public ResponseEntity<Artist> getVenueById(@PathVariable("venueId") @Min(value = 0, message = "Venue ID must be positive") Long venueId){

        return ResponseEntity.ok().build();
    }
}
