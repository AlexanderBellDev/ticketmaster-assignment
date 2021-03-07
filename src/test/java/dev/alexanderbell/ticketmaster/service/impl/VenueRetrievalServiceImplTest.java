package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.VenueRetrievalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VenueRetrievalServiceImplTest {
    @InjectMocks
    VenueRetrievalServiceImpl venueRetrievalService;

    @Mock
    ApiDataRetrievalService apiDataRetrievalService;


    @Test
    void mapOfVenueById() {
        VenueDTO v1 = new VenueDTO(10L, "O2", "o2.com", "London");

        when(apiDataRetrievalService.retrieveListOfVenue()).thenReturn(Collections.singletonList(v1));

        Map<Long,VenueDTO> expectedVenueDTOMap = Collections.singletonMap(10L,v1);
        Map<Long, VenueDTO> actualVenueDTOMap = venueRetrievalService.mapOfVenueById();

        assertEquals(expectedVenueDTOMap,actualVenueDTOMap);
    }
}