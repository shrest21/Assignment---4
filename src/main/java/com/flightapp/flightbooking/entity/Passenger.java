package com.flightapp.flightbooking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String seatNumber;

    @ManyToOne
    private Booking booking;
}
