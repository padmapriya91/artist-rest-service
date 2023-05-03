package com.ticketmaster.artist.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.RequestEntity;

import com.ticketmaster.artist.model.Artist;
import com.ticketmaster.artist.model.ArtistResponse;
import com.ticketmaster.artist.model.Events;
import com.ticketmaster.artist.model.Venue;

public interface ArtistService {
	public CompletableFuture<List<Artist>> getArtists(RequestEntity<?> requestEntity, Integer id);

	public CompletableFuture<List<Events>> getEvents(RequestEntity<?> requestEntity, Integer id);

	public CompletableFuture<List<Venue>> getVenues(RequestEntity<?> requestEntity, Integer id);

	public ArtistResponse getArtistResponse(List<Artist> artists, List<Events> events, List<Venue> venues, Integer id);
}
