package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.EventDataTransformationService;
import dev.alexanderbell.ticketmaster.service.VenueRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventDataTransformationServiceImpl implements EventDataTransformationService {
    private final VenueRetrievalService venueRetrievalService;
    @Override
    public void assignVenueDataForEvent(List<EventDTO> eventDTOList) {
        Map<Long, VenueDTO> venueDataMappedById = venueRetrievalService.mapOfVenueById();
        eventDTOList.forEach(eventDTO -> eventDTO.setVenueDTO(venueDataMappedById.get(eventDTO.getVenue().getId())));
    }
}
