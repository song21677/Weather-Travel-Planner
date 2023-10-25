package com.olaenmanijo.weatherbasedtravelplanner.domain.plan;

import com.fasterxml.jackson.annotation.JsonProperty;

class Response {
	@JsonProperty("body")
    private Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
