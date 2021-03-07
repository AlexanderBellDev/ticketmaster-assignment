package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.exception.ApiNotFoundException;
import dev.alexanderbell.ticketmaster.model.Event;
import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.ArtistIdDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.VenueDataTransformationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VenueDataTransformationServiceImpl implements VenueDataTransformationService {
    private final ModelMapper modelMapper;
    private final ApiDataRetrievalService apiDataRetrievalService;

    public Map<Long, VenueDTO> getVenueDataById() {
        List<VenueDTO> venueDTOS = apiDataRetrievalService.retrieveListOfVenue();
        return venueDTOS.stream()
                .collect(Collectors.toMap(VenueDTO::getId, venueDTO -> venueDTO));
    }

    @Override
    public Venue retrieveVenueWithEvents(Long venueId) {
        List<EventDTO> eventDTOS = apiDataRetrievalService.retrieveListOfEvent();

        Optional<VenueDTO> venueDTOOptional = Optional.ofNullable(getVenueDataById().get(venueId));
        if (venueDTOOptional.isEmpty()) {
            throw new ApiNotFoundException("No venue with id: " + venueId + " found!");
        }

        Venue venue = modelMapper.map(venueDTOOptional.get(), Venue.class);
        List<EventDTO> getRelevantEvents = eventDTOS.stream()
                .filter(eventDTO -> eventDTO.getVenue().getId().equals(venue.getId()))
                .collect(Collectors.toList());

        setArtistDetails(getRelevantEvents);

        List<Event> mappedListOfEvent = getRelevantEvents.stream()
                .map(eventDTO -> modelMapper.map(eventDTO, Event.class))
                .collect(Collectors.toList());

        venue.setEvents(mappedListOfEvent);

        return venue;
    }

    private void setArtistDetails(List<EventDTO> getRelevantEvents) {
        List<ArtistDTO> artistDTOS = apiDataRetrievalService.retrieveListOfArtist();
        Map<Long, ArtistDTO> artistDataById = artistDTOS.stream()
                .collect(Collectors.toMap(ArtistDTO::getId, artistDTO -> artistDTO));

        for (EventDTO eventDTO : getRelevantEvents) {
            Set<ArtistDTO> artistDTOList = new HashSet<>();
            for (ArtistIdDTO artist : eventDTO.getArtists()) {
                artistDTOList.add(artistDataById.get(artist.getId()));
            }
            eventDTO.setArtistSet(artistDTOList);
        }
    }
}
