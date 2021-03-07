package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.exception.ApiNotFoundException;
import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.Event;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.ArtistIdDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.ArtistDataTransformationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistDataTransformationServiceImpl implements ArtistDataTransformationService {
    private final ApiDataRetrievalService apiDataRetrievalService;
    private final ModelMapper modelMapper;
    @Override
    public List<Artist> retrieveListOfArtistWithEvents() {
        List<ArtistDTO> artistDTOS = apiDataRetrievalService.retrieveListOfArtist();
        List<EventDTO> eventDTOS = apiDataRetrievalService.retrieveListOfEvent();


        List<Artist> listOfArtist = artistDTOS.stream()
                .map(artistDTO -> modelMapper.map(artistDTO, Artist.class))
                .collect(Collectors.toList());

        listOfArtist.forEach(artist -> {
            List<Event> listOfMatchingEvent = eventDTOS.stream()
                    .filter(eventDTO -> eventDTO.getArtists().contains(new ArtistIdDTO(artist.getId())))
                    .map(eventDTO -> modelMapper.map(eventDTO, Event.class))
                    .collect(Collectors.toList());

            artist.setEvents(listOfMatchingEvent);
        });


        return listOfArtist;
    }

    @Override
    public Artist retrieveArtistWithEvents(Long artistId) {
        List<Artist> artists = retrieveListOfArtistWithEvents();
        Optional<Artist> optionalArtist = artists.stream()
                .filter(artist -> artist.getId().equals(artistId))
                .findFirst();
        if(optionalArtist.isEmpty()){
            throw new ApiNotFoundException("No artist with id: " + artistId + " found!");
        }

        return optionalArtist.get();
    }
}
