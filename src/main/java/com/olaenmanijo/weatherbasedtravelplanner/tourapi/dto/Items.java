package com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Items {

	@JsonProperty("item")
	private List<Item> item;

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

}
