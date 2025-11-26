package com.flightapp.flightbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pnr;
    private String email;
    private int numberOfSeats;

    private String mealType; // Veg / Non-Veg
    private String status;   // BOOKED / CANCELLED

    @ManyToOne
    private Inventory flight;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Passenger> passengers;
}
