package com.olaenmanijo.weatherbasedtravelplanner.tourapi.dto;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;

@Alias("Item")
public class Item {
	@JsonProperty("title")
    private String title;
	@JsonProperty("addr1")
    private String addr1;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }
}
