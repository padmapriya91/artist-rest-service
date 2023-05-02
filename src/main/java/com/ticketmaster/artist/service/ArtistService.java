package com.ticketmaster.artist.service;

import java.util.List;

import com.ticketmaster.artist.model.Artist;

public interface ArtistService {

	List<Artist> getArtistById(Integer id);
}
