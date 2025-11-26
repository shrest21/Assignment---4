package com.flightapp.flightbooking.service;

import com.flightapp.flightbooking.dto.BookingRequest;
import com.flightapp.flightbooking.dto.PassengerDTO;
import com.flightapp.flightbooking.entity.Booking;
import com.flightapp.flightbooking.entity.Inventory;
import com.flightapp.flightbooking.entity.Passenger;
import com.flightapp.flightbooking.exception.NotFoundException;
import com.flightapp.flightbooking.exception.ValidationException;
import com.flightapp.flightbooking.repository.BookingRepository;
import com.flightapp.flightbooking.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final InventoryRepository inventoryRepository;

    public BookingService(BookingRepository bookingRepository, InventoryRepository inventoryRepository) {
        this.bookingRepository = bookingRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public Booking createBooking(BookingRequest request) {
        Inventory inv = inventoryRepository.findById(request.getInventoryId())
                .orElseThrow(() -> new NotFoundException("Flight not found: " + request.getInventoryId()));

        int seatsRequested = request.getPassengers().size();
        if (inv.getAvailableSeats() < seatsRequested) {
            throw new ValidationException("Not enough seats. Available: " + inv.getAvailableSeats());
        }

        // reduce seats
        inv.setAvailableSeats(inv.getAvailableSeats() - seatsRequested);
        inventoryRepository.save(inv);

        Booking booking = new Booking();
        booking.setFlight(inv);
        booking.setEmail(request.getEmail());
        booking.setNumberOfSeats(seatsRequested);
        booking.setMealType(request.getMealType());
        booking.setStatus("BOOKED");
        String pnr = generatePnr();
        booking.setPnr(pnr);

        // passengers
        List<Passenger> passengerList = new ArrayList<>();
        for (PassengerDTO p : request.getPassengers()) {
            Passenger passenger = new Passenger();
            passenger.setName(p.getName());
            passenger.setAge(p.getAge());
            passenger.setGender(p.getGender());
            passenger.setBooking(booking);
            passengerList.add(passenger);
        }
        booking.setPassengers(passengerList);

        return bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public Booking getByPnr(String pnr) {
        Booking b = bookingRepository.findByPnr(pnr);
        if (b == null) throw new NotFoundException("PNR not found: " + pnr);
        return b;
    }

    @Transactional
    public Booking cancelByPnr(String pnr) {
        Booking b = bookingRepository.findByPnr(pnr);
        if (b == null) throw new NotFoundException("PNR not found: " + pnr);
        if ("CANCELLED".equalsIgnoreCase(b.getStatus())) {
            throw new ValidationException("Already cancelled");
        }
        // restore seats
        Inventory inv = b.getFlight();
        inv.setAvailableSeats(inv.getAvailableSeats() + b.getNumberOfSeats());
        inventoryRepository.save(inv);

        b.setStatus("CANCELLED");
        return bookingRepository.save(b);
    }

    @Transactional(readOnly = true)
    public List<Booking> getHistoryByEmail(String email) {
        return bookingRepository.findByEmail(email);
    }

    private String generatePnr() {
        // simple unique PNR: 8-char alphanumeric
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
}
