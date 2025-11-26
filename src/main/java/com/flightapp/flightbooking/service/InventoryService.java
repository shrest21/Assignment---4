package com.flightapp.flightbooking.service;

import com.flightapp.flightbooking.dto.InventoryAddRequest;
import com.flightapp.flightbooking.entity.Airline;
import com.flightapp.flightbooking.entity.Inventory;
import com.flightapp.flightbooking.exception.NotFoundException;
import com.flightapp.flightbooking.repository.AirlineRepository;
import com.flightapp.flightbooking.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final AirlineRepository airlineRepository;

    public InventoryService(InventoryRepository inventoryRepository, AirlineRepository airlineRepository) {
        this.inventoryRepository = inventoryRepository;
        this.airlineRepository = airlineRepository;
    }

    @Transactional
    public Inventory addInventory(InventoryAddRequest req) {
        Airline airline = airlineRepository.findById(req.getAirlineId())
                .orElseThrow(() -> new NotFoundException("Airline not found: " + req.getAirlineId()));
        Inventory inv = new Inventory();
        inv.setAirline(airline);
        inv.setFromPlace(req.getFromPlace());
        inv.setToPlace(req.getToPlace());
        inv.setFlightDate(req.getFlightDate());
        inv.setDepartureTime(req.getDepartureTime());
        inv.setArrivalTime(req.getArrivalTime());
        inv.setTotalSeats(req.getTotalSeats());
        inv.setAvailableSeats(req.getTotalSeats());
        inv.setPrice(req.getPrice());
        return inventoryRepository.save(inv);
    }
}
