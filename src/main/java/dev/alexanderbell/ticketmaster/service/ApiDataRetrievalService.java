package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;

import java.util.List;

public interface ApiDataRetrievalService {
    List<ArtistDTO> retrieveListOfArtist();

    List<EventDTO> retrieveListOfEvent();

    List<VenueDTO> retrieveListOfVenue();
}
