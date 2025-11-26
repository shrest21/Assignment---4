package com.flightapp.flightbooking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PassengerDTO {
    @NotBlank
    private String name;
    @Min(0)
    private int age;
    @NotBlank
    private String gender;
}
