package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Artist;

import java.util.List;

public interface ArtistDataTransformationService {
    List<Artist> retrieveListOfArtistsWithEventsAssigned();
}
