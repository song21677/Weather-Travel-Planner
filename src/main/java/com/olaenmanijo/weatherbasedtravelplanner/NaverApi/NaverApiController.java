package com.olaenmanijo.weatherbasedtravelplanner.NaverApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
public class NaverApiController {

    @Autowired
    private ReverseGeolocationService reverseGeolocationService;

    @PostMapping("/get-address")
    public ResponseEntity<Map<String, String>> getAddress(@RequestParam double lat, @RequestParam double lon, HttpSession session) throws IOException {
        return ResponseEntity.ok(reverseGeolocationService.getAddress(lat, lon, session));
    }
    
    
}