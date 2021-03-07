package dev.alexanderbell.ticketmaster.controller;

import dev.alexanderbell.ticketmaster.model.Artist;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ArtistController {
    @GetMapping("/artist/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable("artistId") @Min(value = 0, message = "Artist ID must be positive") Integer artistId){

        return ResponseEntity.ok().build();
    }
}
