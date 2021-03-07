package dev.alexanderbell.ticketmaster.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VenueDTO {
    private Long id;
    private String name;
    private String url;
    private String city;

}
