package com.ticketmaster.artist.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ArtistResponse {

	private String name;
	private Integer id;
	private String imgSrc;
	private String url;
	private Integer rank;
	private List<EventResponse> events;
	
}
