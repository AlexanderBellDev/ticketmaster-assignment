package dev.alexanderbell.ticketmaster.model;

import lombok.Data;

import java.util.List;

@Data
public class Event {
    private Long id;
    private String title;
    private String dateStatus;
    private String timeZone;
    private String startDate;

    private List<Artist> artists;
    private Venue venue;

}
