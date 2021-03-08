package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.ApiNoContentException;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiDataRetrievalServiceImplTest {
    @InjectMocks
    ApiDataRetrievalServiceImpl apiDataRetrievalService;
    @Mock
    RestTemplate restTemplate;
    @Value("${artist.endpoint}")
    String artistEndpoint;
    @Value("${event.endpoint}")
    String eventEndpoint;

    @Value("${venue.endpoint}")
    String venueEndpoint;

    @Test
    void retrieveListOfArtist() {
        when(restTemplate.getForObject(artistEndpoint, ArtistDTO[].class)).thenReturn(new ArtistDTO[]{new ArtistDTO(1L, "Bob Marley", "/344.jpg", "bob.com", 10L)});

        List<ArtistDTO> artistDTOList = apiDataRetrievalService.retrieveListOfArtist();
        assertEquals(Collections.singletonList(new ArtistDTO(1L, "Bob Marley", "/344.jpg", "bob.com", 10L)), artistDTOList);
    }

    @Test
    void retrieveListOfArtistNoResponseFromApi() {
        Assertions.assertThrows(ApiNoContentException.class, () -> apiDataRetrievalService.retrieveListOfArtist());
    }

    @Test
    void retrieveListOfEvent() {
        when(restTemplate.getForObject(eventEndpoint, EventDTO[].class)).thenReturn(new EventDTO[]{new EventDTO(1L, "Summer concert", "once", "London", "10/03/2020",
                false,null,null,null,null)});

        List<EventDTO> eventDTOList = apiDataRetrievalService.retrieveListOfEvent();
        assertEquals(Collections.singletonList(new EventDTO(1L, "Summer concert", "once", "London", "10/03/2020",
                false,null,null,null,null)), eventDTOList);
    }

    @Test
    void retrieveListOfEventNoResponseFromApi() {
        List<EventDTO> eventDTOList = apiDataRetrievalService.retrieveListOfEvent();
        assertTrue(eventDTOList.isEmpty());
    }


    @Test
    void retrieveListOfVenue() {
        when(restTemplate.getForObject(venueEndpoint, VenueDTO[].class)).thenReturn(new VenueDTO[]{new VenueDTO(10L,"O2","o2.com","London")});

        List<VenueDTO> venueDTOList = apiDataRetrievalService.retrieveListOfVenue();

        assertEquals(Collections.singletonList(new VenueDTO(10L,"O2","o2.com","London")),venueDTOList);
    }

    @Test
    void retrieveListOfVenueNoResponseFromApi() {
        Assertions.assertThrows(ApiNoContentException.class, () -> apiDataRetrievalService.retrieveListOfVenue());
    }

}