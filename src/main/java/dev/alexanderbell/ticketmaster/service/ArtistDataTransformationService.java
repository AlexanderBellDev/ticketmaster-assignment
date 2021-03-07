package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;

import java.util.List;

public interface ArtistDataTransformationService {
    List<Artist> retrieveListOfArtistWithEvents();

    Artist retrieveArtistWithEvents(Long artistId);

}
