package com.ticketmaster.artist.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ticketmaster.artist.exception.CustomEntityNotFoundException;
import com.ticketmaster.artist.exception.CustomInternalServerError;
import com.ticketmaster.artist.model.Artist;
import com.ticketmaster.artist.model.ArtistResponse;
import com.ticketmaster.artist.model.Events;
import com.ticketmaster.artist.model.Venue;
import com.ticketmaster.artist.service.ArtistService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ArtistController {

	private ArtistService artistService;

	@Autowired
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}

	@GetMapping("/artist/{id}")
	public ResponseEntity<?> getArtistByID(@PathVariable Integer id) {
		log.info("MAin Thread");
		RequestEntity<?> requestEntity = new RequestEntity<>(HttpMethod.GET, null);
		try {
			CompletableFuture<List<Artist>> artists = artistService.getArtists(requestEntity, id);
			CompletableFuture<List<Events>> events = artistService.getEvents(requestEntity, id);
			CompletableFuture<List<Venue>> venues = artistService.getVenues(requestEntity, id);
			ArtistResponse artistResponse = artistService.getArtistResponse(artists.get(), events.get(), venues.get(), id);
			return ResponseEntity.ok().body(artistResponse);
		} catch (CustomEntityNotFoundException e) {
			throw new CustomEntityNotFoundException(e.getMessage());
		} catch (InterruptedException e) {
			throw new CustomInternalServerError(e.getMessage());
		} catch (ExecutionException e) {
			throw new CustomInternalServerError(e.getMessage());
		}
	}
}
