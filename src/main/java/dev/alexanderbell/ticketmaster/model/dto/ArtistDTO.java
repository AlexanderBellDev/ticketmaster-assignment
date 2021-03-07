package dev.alexanderbell.ticketmaster.model.dto;

import lombok.Data;

@Data
public class ArtistDTO {
    private Long id;
    private String name;
    private String imgSrc;
    private String url;
    private Long rank;
}
