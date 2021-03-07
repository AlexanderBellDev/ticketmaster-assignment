package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.VenueDataTransformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VenueDataTransformationServiceImpl implements VenueDataTransformationService {
    private final ApiDataRetrievalService apiDataRetrievalService;
    private Map<Long, VenueDTO> getVenueDataById() {
        List<VenueDTO> venueDTOS = apiDataRetrievalService.retrieveListOfVenue();
        return venueDTOS.stream()
                .collect(Collectors.toMap(VenueDTO::getId, venueDTO -> venueDTO));
    }

    @Override
    public void assignEventObject(List<EventDTO> eventDTOList) {
        Map<Long, VenueDTO> venueDataById = getVenueDataById();

        eventDTOList.forEach(eventDTO -> eventDTO.setVenueObj(venueDataById.get(eventDTO.getVenue().getId())));
    }
}
