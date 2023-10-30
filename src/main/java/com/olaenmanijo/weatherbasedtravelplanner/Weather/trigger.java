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
	
	@Autowired
    private PreSpecialWeatherScheduler prespecialweatherScheduler;
    
	@Autowired
    private SpecialWeatherScheduler specialweatherscheduler;
    


    @GetMapping("/trigger")
    public String triggerScheduler() {
    	shortweatherScheduler.getTrigger();
        return "Scheduler1 task triggered!";
    }
    
    @GetMapping("/trigger2")
    public String triggerScheduler2() {
    	    mediumweatherScheduler.getTrigger();
        return "Scheduler2 task triggered!";
    }
    
    @GetMapping("/trigger3")
    public String triggerScheduler3() {
    	prespecialweatherScheduler.getTrigger();
        return "Scheduler3 task triggered!";
    }
    
    @GetMapping("/trigger4")
    public String triggerScheduler4() {
    	specialweatherscheduler.getTrigger();
        return "Scheduler3 task triggered!";
    }
    
}