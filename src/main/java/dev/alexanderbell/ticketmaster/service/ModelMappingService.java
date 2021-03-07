package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.model.Event;
import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;

import java.util.List;

public interface ModelMappingService {
    List<Artist> artistDTOListToArtistList(List<ArtistDTO> artistDTOS);

    List<Event> eventDTOListToEventList(List<EventDTO> eventDTO);

    Venue venueDTOToVenue(VenueDTO venueDTO);
}
