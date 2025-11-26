package com.flightapp.flightbooking.dto;

import lombok.Data;

@Data
public class InventoryAddRequest {

    private Long airlineId;

    private String fromPlace;
    private String toPlace;

    private String flightName;
    private String departureTime;
    private String arrivalTime;

    private String flightDate;

    private int totalSeats;
    private double price;
}
