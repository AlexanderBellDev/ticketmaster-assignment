package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.ApiNotFoundException;
import dev.alexanderbell.ticketmaster.model.Event;
import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VenueDataTransformationServiceImpl implements VenueDataTransformationService {
    private final ApiDataRetrievalService apiDataRetrievalService;
    private final ModelMappingService modelMappingService;
    private final VenueRetrievalService venueRetrievalService;
    private final ArtistRetrievalService artistRetrievalService;
    @Override
    public Venue retrieveVenueWithEvents(Long venueId) {
        List<EventDTO> eventDTOS = apiDataRetrievalService.retrieveListOfEvent();

        Optional<VenueDTO> venueDTOOptional = Optional.ofNullable(venueRetrievalService.mapOfVenueById().get(venueId));
        if (venueDTOOptional.isEmpty()) {
            throw new ApiNotFoundException("No venue with id: " + venueId + " found!");
        }

        Venue venue = modelMappingService.venueDTOToVenue(venueDTOOptional.get());
        List<EventDTO> getRelevantEvents = eventDTOS.stream()
                .filter(eventDTO -> eventDTO.getVenue().getId().equals(venue.getId()))
                .collect(Collectors.toList());

        artistRetrievalService.setArtistDetails(getRelevantEvents);

        List<Event> mappedListOfEvent = modelMappingService.eventDTOListToEventList(getRelevantEvents);

        venue.setEvents(mappedListOfEvent);

        return venue;
    }
}
