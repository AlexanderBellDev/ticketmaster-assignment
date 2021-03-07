package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;

import java.util.List;
import java.util.Map;

public interface VenueDataTransformationService {
    void assignEventObject(List<EventDTO> eventDTOList);
}
