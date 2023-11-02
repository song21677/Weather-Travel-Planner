package com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Body {
	@JsonProperty("items")
    private Items items;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }
}
