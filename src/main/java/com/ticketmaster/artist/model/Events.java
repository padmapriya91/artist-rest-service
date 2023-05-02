package com.ticketmaster.artist.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Events {

	private String title;
	private Integer id;
	private String singleStatus;
	private String timeZone;
	private String startDate;
	private List<Id> artists;
	private List<Id> venue;
	private Boolean hiddenFromSearch;
}
