package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.EventDataTransformationService;
import dev.alexanderbell.ticketmaster.service.VenueDataTransformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventDataTransformationServiceImpl implements EventDataTransformationService {
    private final VenueDataTransformationService venueDataTransformationService;

    @Override
    public void assignEventObject(List<EventDTO> eventDTOList) {
        Map<Long, VenueDTO> venueDataById = venueDataTransformationService.getVenueDataById();

        eventDTOList.forEach(eventDTO -> eventDTO.setVenueObj(venueDataById.get(eventDTO.getVenue().getId())));
    }
}
