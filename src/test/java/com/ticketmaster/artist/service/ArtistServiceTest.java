package com.ticketmaster.artist.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.ticketmaster.artist.model.Artist;
import com.ticketmaster.artist.model.Events;
import com.ticketmaster.artist.model.Venue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistServiceTest {
	
	@InjectMocks
	private ArtistServiceImpl artistService;
	
	@Value("${interview-data.artists.url}")
	private String artistUrl;
	
	@Value("${interview-data.events.url}")
	private String eventsUrl;
	
	@Value("${interview-data.venues.url}")
	private String venuesUrl;
	
	@Mock
	private RestTemplate restTemplate;
	
	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(artistService, "artistUrl", artistUrl);
		ReflectionTestUtils.setField(artistService, "eventsUrl", eventsUrl);
		ReflectionTestUtils.setField(artistService, "venuesUrl", venuesUrl);
	}

	@Test
    public void getArtists_shouldReturnWith200() {
		Artist[] artists = new Artist[1];
		artists[0] = new Artist().setId(1);
        Mockito.when(restTemplate.getForObject(artistUrl, Artist[].class)).thenReturn(artists);
		CompletableFuture<List<Artist>> artist = artistService.getArtists();
		assertNotNull(artist);
    }
	
	@Test
    public void getEvents_shouldReturnWith200() {
		Events[] events = new Events[1];
		events[0] = new Events().setId(1);
        Mockito.when(restTemplate.getForObject(eventsUrl, Events[].class)).thenReturn(events);
		CompletableFuture<List<Events>> event = artistService.getEvents();
		assertNotNull(event);
    }
	
	@Test
    public void getVenues_shouldReturnWith200() {
		Venue[] venues = new Venue[1];
		venues[0] = new Venue().setId(1);
        Mockito.when(restTemplate.getForObject(venuesUrl, Venue[].class)).thenReturn(venues);
		CompletableFuture<List<Venue>> venue = artistService.getVenues();
		assertNotNull(venue);
    }
}
