package dev.alexanderbell.ticketmaster.service.impl;

import dev.alexanderbell.ticketmaster.model.Artist;
import dev.alexanderbell.ticketmaster.service.ApiDataRetrievalService;
import dev.alexanderbell.ticketmaster.service.ArtistDataTransformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ArtistDataTransformationServiceImpl implements ArtistDataTransformationService {
    private final ApiDataRetrievalService apiDataRetrievalService;

    @Override
    public List<Artist> retrieveListOfArtistWithEvents() {
        return null;
    }
}
