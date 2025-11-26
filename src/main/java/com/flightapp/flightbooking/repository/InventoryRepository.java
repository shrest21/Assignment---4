package com.flightapp.flightbooking.repository;

import com.flightapp.flightbooking.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByFromPlaceIgnoreCaseAndToPlaceIgnoreCase(String from, String to);
}
