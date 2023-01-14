package org.jetsoft.web.jssystemapp.flight.application;

import java.time.LocalDateTime;

public class FlightPublicRowDto {

    private final String flightNumber;
    private final int availablePassengersSeats;
    private final LocalDateTime plannedDeparture;
    private final LocalDateTime plannedArrival;
    private final String sourceCity;
    private final String destinationCity;

    public FlightPublicRowDto(
            String flightNumber,
            int availablePassengersSeats,
            LocalDateTime plannedDeparture,
            LocalDateTime plannedArrival,
            String sourceCity,
            String destinationCity) {

        this.flightNumber = flightNumber;
        this.availablePassengersSeats = availablePassengersSeats;
        this.plannedDeparture = plannedDeparture;
        this.plannedArrival = plannedArrival;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getAvailablePassengersSeats() {
        return availablePassengersSeats;
    }

    public LocalDateTime getPlannedDeparture() {
        return plannedDeparture;
    }

    public LocalDateTime getPlannedArrival() {
        return plannedArrival;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }
}
