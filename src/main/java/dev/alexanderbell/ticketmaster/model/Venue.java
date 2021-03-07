package dev.alexanderbell.ticketmaster.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private Long id;
    private String name;
    private String url;
    private String city;

    private List<Event> events;

}
