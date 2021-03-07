package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.exception.ApiNotFoundException;
import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.ArtistIdDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.ArtistDataTransformationService;
import dev.alexanderbell.ticketmaster.service.ArtistRetrievalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArtistRetrievalServiceImpl implements ArtistRetrievalService {
    private final ArtistDataTransformationService artistDataTransformationService;
    private final ApiDataRetrievalService apiDataRetrievalService;
    @Override
    public Artist retrieveArtistWithEvents(Long artistId) {
        List<Artist> artists = artistDataTransformationService.retrieveListOfArtistsWithEventsAssigned();
        Optional<Artist> optionalArtist = artists.stream()
                .filter(artist -> artist.getId().equals(artistId))
                .findFirst();

        if(optionalArtist.isEmpty()){
            throw new ApiNotFoundException("No artist with id: " + artistId + " found!");
        }

        return optionalArtist.get();
    }

    @Override
    public void setArtistDetails(List<EventDTO> relevantEventList) {
        List<ArtistDTO> artistDTOS = apiDataRetrievalService.retrieveListOfArtist();
        Map<Long, ArtistDTO> artistDataById = artistDTOS.stream()
                .collect(Collectors.toMap(ArtistDTO::getId, artistDTO -> artistDTO));

        for (EventDTO eventDTO : relevantEventList) {
            Set<ArtistDTO> artistDTOList = new HashSet<>();
            for (ArtistIdDTO artist : eventDTO.getArtists()) {
                artistDTOList.add(artistDataById.get(artist.getId()));
            }
            eventDTO.setArtistSet(artistDTOList);
        }
    }
}
