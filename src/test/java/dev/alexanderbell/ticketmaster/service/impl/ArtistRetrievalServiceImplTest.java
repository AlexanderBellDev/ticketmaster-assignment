package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.ApiNotFoundException;
import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.service.ArtistDataTransformationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistRetrievalServiceImplTest {
    @InjectMocks
    ArtistRetrievalServiceImpl artistRetrievalService;
    @Mock
    ArtistDataTransformationService artistDataTransformationService;

    @Test
    void retrieveArtistWithEvents() {
        Artist expectedArtist = new Artist(1L, "Bob Marley", "/344.jpg", "bob.com", 10L, null);
        when(artistDataTransformationService.retrieveListOfArtistsWithEventsAssigned()).thenReturn(new ArrayList<>(List.of(expectedArtist)));

        Artist actualArtist = artistRetrievalService.retrieveArtistWithEvents(1L);

        assertEquals(expectedArtist,actualArtist);
    }

    @Test
    void retrieveArtistWithEventsNotFound() {
        Assertions.assertThrows(ApiNotFoundException.class, () -> artistRetrievalService.retrieveArtistWithEvents(1L));
    }
}