package com.ticketmaster.artist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {

	private String name;
	private Integer id;
	private String imgSrc;
	private String url;
	private Integer rank;

}
