package com.olaenmanijo.weatherbasedtravelplanner.domain.plan;

import com.fasterxml.jackson.annotation.JsonProperty;

class Body {
	@JsonProperty("items")
    private Items items;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }
}
