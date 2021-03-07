package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Artist;

public interface ArtistRetrievalService {
    Artist retrieveArtistWithEvents(Long artistId);
}
