package com.ticketmaster.artist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketmaster.artist.model.Artist;
import com.ticketmaster.artist.model.Events;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Artist> getArtistById(Integer id) {
		RequestEntity<?> requestEntity = new RequestEntity<>(HttpMethod.GET, null);
		ResponseEntity<?> response = restTemplate.exchange(artistUrl, HttpMethod.GET, requestEntity, String.class);
		ResponseEntity<?> eventsResponse = restTemplate.exchange(eventsUrl, HttpMethod.GET, requestEntity,
				String.class);
		ResponseEntity<?> venuesResponse = restTemplate.exchange(venuesUrl, HttpMethod.GET, requestEntity,
				String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<Artist> artists = objectMapper.readValue(String.valueOf(response.getBody()), List.class);
			List<Events> events = objectMapper.readValue(String.valueOf(eventsResponse.getBody()), List.class);
			List<Venue> venues = objectMapper.readValue(String.valueOf(venuesResponse.getBody()), List.class);
			return artists;
		} catch (JsonProcessingException e) {
			log.error("Exception occurred :" + e.getMessage());
		}
		return null;
	}

}
