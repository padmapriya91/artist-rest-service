package com.ticketmaster.artist.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.ticketmaster.artist.model.Artist;
import com.ticketmaster.artist.model.ArtistResponse;
import com.ticketmaster.artist.model.Events;
import com.ticketmaster.artist.model.Venue;

public interface ArtistService {
	/**
	 * Method to get all artists
	 * @return {@link CompletableFuture} of {@link List} of  {@link Artist}
	 */
	public CompletableFuture<List<Artist>> getArtists();

	/**
	 * Method to get all events
	 * @return {@link CompletableFuture} of {@link List} of  {@link Events}
	 */
	public CompletableFuture<List<Events>> getEvents();

	/**
	 * Method to get all venues
	 * @return {@link CompletableFuture} of {@link List} of  {@link Venue}
	 */
	public CompletableFuture<List<Venue>> getVenues();

	/**
	 * Method to get the artist information including all the events the artist will perform at.
	 * @param artists {@link List} of {@link Artist}
	 * @param events {@link List} of {@link Events}
	 * @param venues {@link List} of {@link Venue}
	 * @param id {@link Integer}
	 * @return {@link ArtistResponse}
	 */
	public ArtistResponse getArtistResponse(List<Artist> artists, List<Events> events, List<Venue> venues, Integer id);
}
