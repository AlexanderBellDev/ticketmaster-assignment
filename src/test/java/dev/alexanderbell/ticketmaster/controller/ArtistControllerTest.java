package dev.alexanderbell.ticketmaster.controller;

import dev.alexanderbell.ticketmaster.service.ArtistDataTransformationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ArtistDataTransformationService artistDataTransformationService;

    final String URL_TEMPLATE = "/api/v1/artist/";

    @Test
    void testEnsureEndpointIsExposed() throws Exception {
        int artistId = 10;
        MockHttpServletRequestBuilder REQUEST_BUILDER = request(HttpMethod.GET, URL_TEMPLATE + artistId)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        mockMvc.perform(REQUEST_BUILDER)
                .andExpect(status().isOk());
    }

    @Test
    void testEnsureNegativeVenueIdIsRejected() throws Exception {
        int artistId = -10;

        MockHttpServletRequestBuilder REQUEST_BUILDER = request(HttpMethod.GET, URL_TEMPLATE + artistId)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        mockMvc.perform(REQUEST_BUILDER)
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("getArtistById.artistId: Artist ID must be positive"));
    }


}