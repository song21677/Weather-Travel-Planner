package com.olaenmanijo.weatherbasedtravelplanner.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather-scheduler")
public class trigger {

	@Autowired
    private ShortWeatherScheduler shortweatherScheduler;
	
	@Autowired
    private MediumWeatherScheduler mediumweatherScheduler;
    
    


    @GetMapping("/trigger")
    public String triggerScheduler() {
    	shortweatherScheduler.scheduledTask();
        return "Scheduler task triggered!";
    }
    
    @GetMapping("/trigger2")
    public String triggerScheduler2() {
    	mediumweatherScheduler.scheduledTask();
        return "Scheduler task triggered!";
    }
    
}