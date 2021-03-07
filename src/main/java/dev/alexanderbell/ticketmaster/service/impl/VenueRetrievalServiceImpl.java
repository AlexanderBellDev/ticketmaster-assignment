package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.VenueRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class VenueRetrievalServiceImpl implements VenueRetrievalService {
    private final ApiDataRetrievalService apiDataRetrievalService;

    @Override
    public Map<Long, VenueDTO> mapOfVenueById() {
        List<VenueDTO> venueDTOS = apiDataRetrievalService.retrieveListOfVenue();
        return venueDTOS.stream()
                .collect(Collectors.toMap(VenueDTO::getId, venueDTO -> venueDTO));
    }
}
