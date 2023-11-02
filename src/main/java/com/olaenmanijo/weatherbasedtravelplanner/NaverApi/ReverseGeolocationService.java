package com.olaenmanijo.weatherbasedtravelplanner.NaverApi;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

public interface ReverseGeolocationService {
	
	public Map<String, String> getAddress(@RequestParam double lat, @RequestParam double lon,HttpSession session) throws IOException;

}
