package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.Event;
import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import dev.alexanderbell.ticketmaster.service.ModelMappingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ModelMappingServiceImpl implements ModelMappingService {
    private final ModelMapper modelMapper;

    @Override
    public List<Artist> artistDTOListToArtistList(List<ArtistDTO> artistDTOS) {
        return artistDTOS.stream()
                .map(artistDTO -> modelMapper.map(artistDTO, Artist.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> eventDTOListToEventList(List<EventDTO> eventDTOS) {
        return eventDTOS.stream()
                .map(eventDTO -> modelMapper.map(eventDTO, Event.class))
                .collect(Collectors.toList());
    }

    @Override
    public Venue venueDTOToVenue(VenueDTO venueDTO) {
        return modelMapper.map(venueDTO,Venue.class);
    }
}
