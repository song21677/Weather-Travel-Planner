package com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	@JsonProperty("body")
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
