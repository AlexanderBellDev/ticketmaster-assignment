package dev.alexanderbell.ticketmaster.service;

import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;

import java.util.Map;

public interface VenueRetrievalService
{
    Map<Long, VenueDTO> mapOfVenueById();
}
