package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.Venue;

public interface VenueDataTransformationService {
    Venue retrieveVenueWithEvents(Long venueId);
}
