package com.flightapp.flightbooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "flights")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airline;
    private String source;
    private String destination;
    private double price;
    private String departureTime;
    private String arrivalTime;
}
