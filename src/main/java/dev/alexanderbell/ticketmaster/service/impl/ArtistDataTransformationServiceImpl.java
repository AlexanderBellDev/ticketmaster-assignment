package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.ArtistIdDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.ArtistDataTransformationService;
import dev.alexanderbell.ticketmaster.service.EventDataTransformationService;
import dev.alexanderbell.ticketmaster.service.ModelMappingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistDataTransformationServiceImpl implements ArtistDataTransformationService {
    private final ApiDataRetrievalService apiDataRetrievalService;
    private final EventDataTransformationService eventDataTransformationService;
    private final ModelMapper modelMapper;
    private final ModelMappingService modelMappingService;

    @Override
    public List<Artist> retrieveListOfArtistsWithEventsAssigned() {
        List<ArtistDTO> artistDTOS = apiDataRetrievalService.retrieveListOfArtist();
        List<EventDTO> eventDTOS = apiDataRetrievalService.retrieveListOfEvent();

        eventDataTransformationService.assignVenueDataForEvent(eventDTOS);

        List<Artist> listOfArtist = modelMappingService.artistDTOToArtist(artistDTOS);

        listOfArtist.forEach(artist -> {
            List<EventDTO> listOfMatchingEvent = eventDTOS.stream()
                    .filter(eventDTO -> eventDTO.getArtists().contains(new ArtistIdDTO(artist.getId())))
                    .collect(Collectors.toList());
            artist.setEvents( modelMappingService.eventDTOToEvent(listOfMatchingEvent));
        });


        return listOfArtist;
    }
}
