package com.flightapp.flightbooking.repository;

import com.flightapp.flightbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByPnr(String pnr);
    List<Booking> findByEmail(String email);
}
