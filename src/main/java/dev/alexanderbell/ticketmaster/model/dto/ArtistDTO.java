package dev.alexanderbell.ticketmaster.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArtistDTO {
    private Long id;
    private String name;
    private String imgSrc;
    private String url;
    private Long rank;
}
