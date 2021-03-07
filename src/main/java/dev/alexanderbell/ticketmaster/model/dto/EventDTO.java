package dev.alexanderbell.ticketmaster.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String dateStatus;
    private String timeZone;
    private String startDate;

    private List<Long> artists;
    private Long venue;

}
