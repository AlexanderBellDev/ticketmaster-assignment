package dev.alexanderbell.ticketmaster.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private Long id;
    private String name;
    private String imgSrc;
    private String url;
    private Long rank;

    private List<Event> events;
}
