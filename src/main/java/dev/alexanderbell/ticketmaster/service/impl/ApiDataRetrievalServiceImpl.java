package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.exception.ApiNoContentException;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiDataRetrievalServiceImpl implements ApiDataRetrievalService {
    private final RestTemplate restTemplate;

    @Value("${artist.endpoint}")
    String artistEndpoint;

    @Value("${event.endpoint}")
    String eventEndpoint;

    @Value("${venue.endpoint}")
    String venueEndpoint;

    @Override
    public List<ArtistDTO> retrieveListOfArtist() {
       Optional<ArtistDTO[]> artistDTOList = Optional.ofNullable(restTemplate.getForObject(artistEndpoint, ArtistDTO[].class));
        if(artistDTOList.isEmpty()){
            throw new ApiNoContentException("No content returned from TicketMaster artist API");
        }
        return Arrays.asList(artistDTOList.get());
    }

    public List<EventDTO> retrieveListOfEvent() {
        Optional<EventDTO[]> eventDTOList = Optional.ofNullable(restTemplate.getForObject(eventEndpoint, EventDTO[].class));
        if(eventDTOList.isEmpty()){
            return new ArrayList<>();
        }
        return Arrays.asList(eventDTOList.get());
    }

    public List<VenueDTO> retrieveListOfVenue() {
        Optional<VenueDTO[]> venueDTOList = Optional.ofNullable(restTemplate.getForObject(venueEndpoint, VenueDTO[].class));
        if(venueDTOList.isEmpty()){
            throw new ApiNoContentException("No content returned from TicketMaster venue API");
        }
        return Arrays.asList(venueDTOList.get());
    }
}
