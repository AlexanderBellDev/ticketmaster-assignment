package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueIdDTO;
import dev.alexanderbell.ticketmaster.service.VenueDataTransformationService;
import dev.alexanderbell.ticketmaster.service.VenueRetrievalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventDataTransformationServiceImplTest {
    @InjectMocks
    EventDataTransformationServiceImpl eventDataTransformationService;

    @Mock
    VenueRetrievalService venueRetrievalService;

    @Test
    void assignVenueDataForEvent() {
        VenueDTO v1 = new VenueDTO(10L, "O2", "o2.com", "London");
        when(venueRetrievalService.mapOfVenueById()).thenReturn(new HashMap<>(Map.of(10L, v1)));

        EventDTO e1 = new EventDTO(1L, "Summer concert", "once", "London", "10/03/2020",
                false, null, new VenueIdDTO(10L), null, null);
        ArrayList<EventDTO> eventDTOS = new ArrayList<>(List.of(e1));

        eventDataTransformationService.assignVenueDataForEvent(eventDTOS);

        assertEquals(v1,e1.getVenueDTO());
    }


}