package org.jetsoft.web.jssystemapp.flight.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightEmployeeRowDto {

    private final Long flightId;
    private final String flightNumber;
    private final int availablePassengersSeats;
    private final LocalDateTime plannedDeparture;
    private final LocalDateTime plannedArrival;
    private final String sourceCity;
    private final String destinationCity;
    private final String sourceNationality;
    private final String destinationNationality;
    private final boolean active;

    public FlightEmployeeRowDto(
            Long flightId,
            String flightNumber,
            int availablePassengersSeats,
            LocalDateTime plannedDeparture,
            LocalDateTime plannedArrival,
            String sourceCity,
            String destinationCity,
            String sourceNationality,
            String destinationNationality,
            boolean active) {

        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.availablePassengersSeats = availablePassengersSeats;
        this.plannedDeparture = plannedDeparture;
        this.plannedArrival = plannedArrival;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.sourceNationality = sourceNationality;
        this.destinationNationality = destinationNationality;
        this.active = active;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getAvailablePassengersSeats() {
        return availablePassengersSeats;
    }

    public String getPlannedDeparture() {
        return DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm").format(plannedDeparture);
    }

    public String getPlannedArrival() {
        return DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm").format(plannedArrival);
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public String getSourceNationality() {
        return sourceNationality;
    }

    public String getDestinationNationality() {
        return destinationNationality;
    }

    public Long getFlightId() {
        return flightId;
    }

    public boolean isActive() {
        return active;
    }
}
