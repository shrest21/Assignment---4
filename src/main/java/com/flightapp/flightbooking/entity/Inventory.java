package com.flightapp.flightbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // flight details
    private String flightName;

    private String fromPlace;
    private String toPlace;

    private String departureTime;
    private String arrivalTime;

    private String flightDate;

    // seats
    private int totalSeats;
    private int availableSeats;

    private double price;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
}
