package dev.alexanderbell.ticketmaster.model.dto;

import lombok.Data;

@Data
public class VenueDTO {
    private Long id;
    private String name;
    private String url;
    private String city;

}
