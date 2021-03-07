package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.Event;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueIdDTO;
import dev.alexanderbell.ticketmaster.service.ModelMappingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModelMappingServiceImplTest {
    @InjectMocks
    ModelMappingServiceImpl modelMappingService;

    @Mock
    ModelMapper modelMapper;

    @Test
    void artistDTOToArtist() {
        ArtistDTO artistDTO = new ArtistDTO(1L, "Bob Marley", "/344.jpg", "bob.com", 10L);
        Artist expectedArtist = new Artist(1L, "Bob Marley", "/344.jpg", "bob.com", 10L, null);
        when(modelMapper.map(artistDTO,Artist.class))
                .thenReturn(expectedArtist);

        List<Artist> actualArtists = modelMappingService.artistDTOToArtist(Collections.singletonList(artistDTO));
        assertEquals(expectedArtist,actualArtists.get(0));
    }

    @Test
    void eventDTOToEvent() {
        EventDTO e1 = new EventDTO(1L, "Summer concert", "once", "London", "10/03/2020",
                false, null, new VenueIdDTO(10L), null, null);
        Event expectedEvent = new Event(1L, "Summer concert", "once", "London", "10/03/2020",
                null, null);
        when(modelMapper.map(e1,Event.class))
                .thenReturn(expectedEvent);

        List<Event> events = modelMappingService.eventDTOToEvent(Collections.singletonList(e1));

        assertEquals(expectedEvent,events.get(0));
    }
}