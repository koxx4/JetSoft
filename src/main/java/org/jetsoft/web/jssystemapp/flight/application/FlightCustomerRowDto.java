package org.jetsoft.web.jssystemapp.flight.application;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record FlightCustomerRowDto(
    Long flightId,
    String flightNumber,
    Long availablePassengersSeats,
    @DateTimeFormat(pattern = "EEE, d MMM yyyy HH:mm")
    LocalDateTime plannedDeparture,
    @DateTimeFormat(pattern = "EEE, d MMM yyyy HH:mm")
    LocalDateTime plannedArrival,
    String sourceCity,
    String destinationCity,
    String sourceNationality,
    String destinationNationality) {}
