package dev.alexanderbell.ticketmaster.model;

import lombok.Data;

import java.util.List;

@Data
public class Venue {
    private Long id;
    private String name;
    private String url;
    private String city;

    private List<Event> events;

}
