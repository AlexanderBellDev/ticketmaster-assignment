package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.ApiNotFoundException;
import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.ArtistIdDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueIdDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.ArtistDataTransformationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistRetrievalServiceImplTest {
    @InjectMocks
    ArtistRetrievalServiceImpl artistRetrievalService;
    @Mock
    ArtistDataTransformationService artistDataTransformationService;
    @Mock
    ApiDataRetrievalService apiDataRetrievalService;

    @Test
    void retrieveArtistWithEvents() {
        Artist expectedArtist = new Artist(1L, "Bob Marley", "/344.jpg", "bob.com", 10L, null);
        when(artistDataTransformationService.retrieveListOfArtistsWithEventsAssigned()).thenReturn(Collections.singletonList(expectedArtist));

        Artist actualArtist = artistRetrievalService.retrieveArtistWithEvents(1L);

        assertEquals(expectedArtist, actualArtist);
    }

    @Test
    void retrieveArtistWithEventsNotFound() {
        Assertions.assertThrows(ApiNotFoundException.class, () -> artistRetrievalService.retrieveArtistWithEvents(1L));
    }

    @Test
    void setArtistDetails() {
        List<ArtistDTO> artistDTOS = Collections.singletonList(new ArtistDTO(1L, "Bob Marley", "/344.jpg", "bob.com", 10L));
        EventDTO eventDTO = new EventDTO(1L, "Summer concert", "once", "London", "10/03/2020",
                false, new HashSet<>(Set.of(new ArtistIdDTO(1L))), new VenueIdDTO(10L), null, null);
        List<EventDTO> eventDTOS = Collections.singletonList(eventDTO);
        when(apiDataRetrievalService.retrieveListOfArtist()).thenReturn(artistDTOS);

        artistRetrievalService.setArtistDetails(eventDTOS);

        assertEquals(eventDTO.getArtistDTOSet(), new HashSet<>(Set.of(artistDTOS.get(0))));
    }
}