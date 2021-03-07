package dev.alexanderbell.ticketmaster.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.alexanderbell.ticketmaster.model.dto.VenueDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    private Long id;
    private String title;
    private String dateStatus;
    private String timeZone;
    private String startDate;

    private List<Artist> artists;
    private VenueDTO venue;

}
