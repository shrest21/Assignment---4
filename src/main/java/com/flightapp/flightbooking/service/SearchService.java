package com.flightapp.flightbooking.service;

import com.flightapp.flightbooking.dto.SearchRequest;
import com.flightapp.flightbooking.entity.Inventory;
import com.flightapp.flightbooking.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final InventoryRepository inventoryRepository;

    public SearchService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> search(SearchRequest req) {
        // simplest: search by from & to (case-insensitive)
        return inventoryRepository.findByFromPlaceIgnoreCaseAndToPlaceIgnoreCase(req.getFromPlace(), req.getToPlace());
    }
}
