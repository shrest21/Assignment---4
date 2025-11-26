package com.flightapp.flightbooking.controller;

import com.flightapp.flightbooking.dto.BookingRequest;
import com.flightapp.flightbooking.entity.Booking;
import com.flightapp.flightbooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/flight")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking/{flightId}")
    public ResponseEntity<Booking> book(@PathVariable("flightId") Long flightId,
                                        @Valid @RequestBody BookingRequest req) {
        // ensure path flightId matches body inventoryId if present
        req.setInventoryId(flightId);
        Booking b = bookingService.createBooking(req);
        return ResponseEntity.ok(b);
    }

    @GetMapping("/ticket/{pnr}")
    public ResponseEntity<Booking> ticket(@PathVariable String pnr) {
        Booking b = bookingService.getByPnr(pnr);
        return ResponseEntity.ok(b);
    }

    @GetMapping("/booking/history/{emailId}")
    public ResponseEntity<List<Booking>> history(@PathVariable String emailId) {
        List<Booking> list = bookingService.getHistoryByEmail(emailId);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/booking/cancel/{pnr}")
    public ResponseEntity<String> cancel(@PathVariable String pnr) {
        bookingService.cancelByPnr(pnr);
        return ResponseEntity.ok("Cancelled");
    }
}
