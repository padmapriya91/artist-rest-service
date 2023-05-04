package com.ticketmaster.artist.controller;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistControllerTest {

	@LocalServerPort
    private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    
    @Test
    public void validArtistID_shouldReturnWith200() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/artist/21"), HttpMethod.GET, entity, String.class);

        String expected = "{\"name\":\"HRH Prog\",\"id\":21,\"imgSrc\":\"//some-base-url/hrh-prog.jpg\",\"url\":\"/hrh-prog-tickets/artist/21\",\"rank\":1,\"events\":[{\"title\":\"Fusion Prog\",\"venue\":\"O2 Academy Sheffield,Sheffield\"},{\"title\":\"A festival Live\",\"venue\":\"O2 Academy Brixton,London\"},{\"title\":\"Huge Live\",\"venue\":\"O2 Academy Sheffield,Sheffield\"}]}";

        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }
    
    @Test
    public void validArtistID_failWith404() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/artist/2111"), HttpMethod.GET, entity, String.class);

        String expected = "{\"status\":404,\"message\":\"No such artist found with ID: 2111\",\"success\":false}";

        assertEquals(expected, response.getBody());
        assertEquals(404, response.getStatusCode().value());
    }
    
    @Test
    public void validArtistID_no_venue_failWith404() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/artist/29"), HttpMethod.GET, entity, String.class);

        String expected = "{\"status\":404,\"message\":\"No mapping venue found for the artist with ID: 29\",\"success\":false}";

        assertEquals(expected, response.getBody());
        assertEquals(404, response.getStatusCode().value());
    }
    
    private URI createURLWithPort(String uri) {
        return URI.create("http://localhost:" + port + uri);
    }
}
