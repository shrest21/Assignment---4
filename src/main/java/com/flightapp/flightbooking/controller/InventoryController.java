package com.flightapp.flightbooking.controller;

import com.flightapp.flightbooking.dto.InventoryAddRequest;
import com.flightapp.flightbooking.entity.Inventory;
import com.flightapp.flightbooking.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping
    public String addFlight(@RequestBody InventoryAddRequest req) {

        Inventory inv = new Inventory();
        inv.setFromPlace(req.getFromPlace());
        inv.setToPlace(req.getToPlace());
        inv.setFlightName(req.getFlightName());
        inv.setDepartureTime(req.getDepartureTime());
        inv.setArrivalTime(req.getArrivalTime());
        inv.setPrice(req.getPrice());

        inventoryRepository.save(inv);

        return "Flight added successfully";
    }
}
