package com.flightapp.flightbooking.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    @NotNull
    private Long inventoryId;

    @Email
    private String email;

    @NotEmpty
    @Valid
    private List<PassengerDTO> passengers;

    // optional
    private String mealType;
}
