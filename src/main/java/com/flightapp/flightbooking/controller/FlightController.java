package com.flightapp.flightbooking.controller;

import com.flightapp.flightbooking.entity.Flight;
import com.flightapp.flightbooking.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // GET all flights
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    // POST create a new flight
    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }
}
