package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.ApiNotFoundException;
import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.service.ArtistDataTransformationService;
import dev.alexanderbell.ticketmaster.service.ArtistRetrievalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ArtistRetrievalServiceImpl implements ArtistRetrievalService {
    private final ArtistDataTransformationService artistDataTransformationService;
    @Override
    public Artist retrieveArtistWithEvents(Long artistId) {
        List<Artist> artists = artistDataTransformationService.retrieveListOfArtistsWithEventsAssigned();
        Optional<Artist> optionalArtist = artists.stream()
                .filter(artist -> artist.getId().equals(artistId))
                .findFirst();

        if(optionalArtist.isEmpty()){
            throw new ApiNotFoundException("No artist with id: " + artistId + " found!");
        }

        return optionalArtist.get();
    }
}
