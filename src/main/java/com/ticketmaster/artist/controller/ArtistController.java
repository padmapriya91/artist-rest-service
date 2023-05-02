package com.ticketmaster.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ticketmaster.artist.model.Artist;
import com.ticketmaster.artist.service.ArtistService;

@RestController
public class ArtistController {

	private ArtistService artistService;

	@Autowired
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}

	@GetMapping("/artist/{id}")
	public ResponseEntity<?> getArtistByID(@PathVariable Integer id) {
		List<Artist> artists = artistService.getArtistById(id);

		return ResponseEntity.ok().body(artists);
	}
}
