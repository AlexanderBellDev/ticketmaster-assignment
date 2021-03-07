package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.Event;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.ArtistIdDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueIdDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.EventDataTransformationService;
import dev.alexanderbell.ticketmaster.service.ModelMappingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistDataTransformationServiceImplTest {
    @InjectMocks
    ArtistDataTransformationServiceImpl artistDataTransformationService;
    @Mock
    ApiDataRetrievalService apiDataRetrievalService;
    @Mock
    EventDataTransformationService eventDataTransformationService;
    @Mock
    ModelMappingService modelMappingService;

    @Test
    void retrieveListOfArtistWithEvents() {
        ArrayList<ArtistDTO> artistDTOS = new ArrayList<>(List.of(new ArtistDTO(1L, "Bob Marley", "/344.jpg", "bob.com", 10L)));
        EventDTO e1 = new EventDTO(1L, "Summer concert", "once", "London", "10/03/2020",
                false, new HashSet<>(Set.of(new ArtistIdDTO(1L))), new VenueIdDTO(10L), null, null);
        ArrayList<EventDTO> eventDTOS = new ArrayList<>(List.of(e1));
        when(apiDataRetrievalService.retrieveListOfEvent()).thenReturn(eventDTOS);
        when(apiDataRetrievalService.retrieveListOfArtist()).thenReturn(artistDTOS);
        Artist expectedArtist = new Artist(1L, "Bob Marley", "/344.jpg", "bob.com", 10L, null);


        when(modelMappingService.artistDTOListToArtistList(artistDTOS)).thenReturn(new ArrayList<>(List.of(expectedArtist)));
        List<Event> expectedEventList = new ArrayList<>(List.of(new Event(1L, "Summer concert", "once", "London", "10/03/2020",
                new HashSet<>(Set.of(artistDTOS.get(0))), null)));
        when(modelMappingService.eventDTOListToEventList(eventDTOS)).thenReturn(expectedEventList);
        List<Artist> artists = artistDataTransformationService.retrieveListOfArtistsWithEventsAssigned();
        assertEquals(expectedEventList,artists.get(0).getEvents());
    }
}