package dev.alexanderbell.ticketmaster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.alexanderbell.ticketmaster.model.dto.ArtistDTO;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    private Long id;
    private String title;
    private String dateStatus;
    private String timeZone;
    private String startDate;
    @JsonProperty("artists")
    private Set<ArtistDTO> artistSet;
    private VenueDTO venue;

}
