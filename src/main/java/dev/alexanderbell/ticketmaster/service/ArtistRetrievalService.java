package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;

import java.util.List;

public interface ArtistRetrievalService {
    Artist retrieveArtistWithEvents(Long artistId);

    void setArtistDetails(List<EventDTO> getRelevantEvents);
}
