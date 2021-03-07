package dev.alexanderbell.ticketmaster.controller;

import dev.alexanderbell.ticketmaster.service.VenueDataTransformationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VenueController.class)
class VenueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    VenueDataTransformationService venueDataTransformationService;

    final String URL_TEMPLATE = "/api/v1/venue/";

    @Test
    void testEnsureEndpointIsExposed() throws Exception {
        int venueId = 5;
        MockHttpServletRequestBuilder REQUEST_BUILDER = request(HttpMethod.GET, URL_TEMPLATE + venueId)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        mockMvc.perform(REQUEST_BUILDER)
                .andExpect(status().isOk());
    }

    @Test
    void testEnsureNegativeVenueIdIsRejected() throws Exception {
        int venueId = -10;
        MockHttpServletRequestBuilder REQUEST_BUILDER = request(HttpMethod.GET, URL_TEMPLATE + venueId)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        mockMvc.perform(REQUEST_BUILDER)
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("getVenueById.venueId: Venue ID must be positive"));
    }


}