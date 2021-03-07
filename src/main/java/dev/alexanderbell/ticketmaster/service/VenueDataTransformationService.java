package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Venue;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;

import java.util.Map;

public interface VenueDataTransformationService {
    Map<Long, VenueDTO> getVenueDataById();

    Venue retrieveVenueWithEvents(Long venueId);
}
