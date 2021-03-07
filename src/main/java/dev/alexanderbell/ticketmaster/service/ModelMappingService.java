package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.Event;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;

import java.util.List;

public interface ModelMappingService {
    List<Artist> artistDTOToArtist(List<ArtistDTO> artistDTOS);

    List<Event> eventDTOToEvent(List<EventDTO> eventDTO);
}
