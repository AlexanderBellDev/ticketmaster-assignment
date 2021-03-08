package dev.alexanderbell.ticketmaster.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private String title;
    private String dateStatus;
    private String timeZone;
    private String startDate;
    private boolean hiddenFromSearch;

    private Set<ArtistIdDTO> artists;
    private VenueIdDTO venue;

    private VenueDTO venueDTO;
    private Set<ArtistDTO> artistDTOSet;
}
