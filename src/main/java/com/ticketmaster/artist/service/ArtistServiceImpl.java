package com.ticketmaster.artist.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.ticketmaster.artist.exception.CustomEntityNotFoundException;
import com.ticketmaster.artist.model.Artist;
import com.ticketmaster.artist.model.ArtistResponse;
import com.ticketmaster.artist.model.EventResponse;
import com.ticketmaster.artist.model.Events;
import com.ticketmaster.artist.model.Id;
import com.ticketmaster.artist.model.Venue;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArtistServiceImpl implements ArtistService {

	@Value("${interview-data.artists.url}")
	private String artistUrl;
	@Value("${interview-data.events.url}")
	private String eventsUrl;
	@Value("${interview-data.venues.url}")
	private String venuesUrl;
	@Autowired
	private RestTemplate restTemplate;
	
	@Async
	@Override
	public CompletableFuture<List<Artist>> getArtists(RequestEntity<?> requestEntity, Integer id){
		log.info("Artist Thread");
		Artist[] artistResponse = restTemplate.getForObject(artistUrl, Artist[].class);
		return CompletableFuture.completedFuture(Arrays.asList(artistResponse));
	}

	@Override
	@Async
	public CompletableFuture<List<Events>> getEvents(RequestEntity<?> requestEntity, Integer id){
		log.info("Event Thread");
		Events[] eventsResponse = restTemplate.getForObject(eventsUrl, Events[].class);
		return CompletableFuture.completedFuture(Arrays.asList(eventsResponse));
	}
	
	@Override
	@Async
	public CompletableFuture<List<Venue>> getVenues(RequestEntity<?> requestEntity, Integer id){
		log.info("Venue Thread");
		Venue[] venuesResponse = restTemplate.getForObject(venuesUrl, Venue[].class);
		return CompletableFuture.completedFuture(Arrays.asList(venuesResponse));
	}
	
	@Override
	public ArtistResponse getArtistResponse(List<Artist> artists, List<Events> events,
			List<Venue> venues, Integer id) {
		List<EventResponse> eventResponses = new ArrayList<>();
		Artist artistInfo = artists.stream().filter(artist -> artist.getId() == id).findAny()
				.orElseThrow(() -> new CustomEntityNotFoundException("No such artist found with ID: " + id));
		events.stream().forEach(event -> {
			List<Id> ids = event.getArtists().stream().filter(eventArtists -> eventArtists.getId() == id)
					.collect(Collectors.toList());
			if (!CollectionUtils.isEmpty(ids)) {
				Venue venueResponse;
				venueResponse = venues.stream().filter(venue -> venue.getId() == event.getVenue().getId()).findAny()
						.orElseThrow(() -> new CustomEntityNotFoundException("No mapping venue found for the artist with ID: " + id));
				eventResponses.add(new EventResponse().setTitle(event.getTitle())
						.setVenue(venueResponse.getName() + "," + venueResponse.getCity()));
			}
			else
				throw new CustomEntityNotFoundException("No events found for the artist with ID: " + id);
		});

		ArtistResponse response = new ArtistResponse().setId(id).setName(artistInfo.getName())
				.setUrl(artistInfo.getUrl()).setImgSrc(artistInfo.getImgSrc()).setRank(artistInfo.getRank())
				.setEvents(eventResponses);

		return response;
	}
}
