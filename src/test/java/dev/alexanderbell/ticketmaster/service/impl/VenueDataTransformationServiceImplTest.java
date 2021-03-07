package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.ApiNoContentException;
import dev.alexanderbell.ticketmaster.exception.ApiNotFoundException;
import dev.alexanderbell.ticketmaster.model.Event;
import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.*;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.ArtistRetrievalService;
import dev.alexanderbell.ticketmaster.service.ModelMappingService;
import dev.alexanderbell.ticketmaster.service.VenueRetrievalService;
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
class VenueDataTransformationServiceImplTest {
    @InjectMocks
    VenueDataTransformationServiceImpl venueDataTransformationService;

    @Mock
    ApiDataRetrievalService apiDataRetrievalService;
    @Mock
    ModelMappingService modelMappingService;
    @Mock
    VenueRetrievalService venueRetrievalService;
    @Mock
    ArtistRetrievalService artistRetrievalService;


    @Test
    void getVenueDataById() {
        VenueDTO v1 = new VenueDTO(10L, "O2", "o2.com", "London");
        EventDTO e1 = new EventDTO(1L, "Summer concert", "once", "London", "10/03/2020",
                false, null, new VenueIdDTO(10L), v1, null);
        ArrayList<EventDTO> eventDTOS = new ArrayList<>(List.of(e1));
        when(apiDataRetrievalService.retrieveListOfEvent()).thenReturn(eventDTOS);

        when(venueRetrievalService.mapOfVenueById()).thenReturn(new HashMap<>(Map.of(1L, v1)));
        when(modelMappingService.venueDTOToVenue(v1)).thenReturn(new Venue(10L, "O2", "o2.com", "London",null));
        when(modelMappingService.eventDTOListToEventList(eventDTOS)).thenReturn(Collections.singletonList(new Event(1L, "Summer concert", "once", "London", "10/03/2020",
               null, null)));
        Venue expectedVenue = new Venue(10L, "O2", "o2.com", "London",Collections.singletonList(new Event(1L, "Summer concert", "once", "London", "10/03/2020",
                null, null)));
        Venue actualVenue = venueDataTransformationService.retrieveVenueWithEvents(1L);

        assertEquals(expectedVenue,actualVenue);

    }

    @Test
    void getVenueDataByIdNotFound() {
        Assertions.assertThrows(ApiNotFoundException.class, () -> venueDataTransformationService.retrieveVenueWithEvents(1L));
    }
}