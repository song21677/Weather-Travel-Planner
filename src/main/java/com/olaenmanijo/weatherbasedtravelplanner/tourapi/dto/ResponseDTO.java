package com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO {
	@JsonProperty("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
