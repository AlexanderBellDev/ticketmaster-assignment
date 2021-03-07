package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.EventDTO;

import java.util.List;

public interface EventDataTransformationService {
    void assignEventObject(List<EventDTO> eventDTOList);
}
