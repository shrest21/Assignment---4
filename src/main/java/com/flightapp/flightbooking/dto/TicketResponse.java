package com.flightapp.flightbooking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TicketResponse {
    private String pnr;
    private String email;
    private String status;
    private Long bookingId;
    private List<PassengerDTO> passengers;
    private String flightInfo;
    private double amount;
}
