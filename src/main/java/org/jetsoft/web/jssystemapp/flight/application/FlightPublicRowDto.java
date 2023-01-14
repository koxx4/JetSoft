package org.jetsoft.web.jssystemapp.flight.application;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightPublicRowDto {

    private final String flightNumber;
    private final int availablePassengersSeats;
    private final LocalDateTime plannedDeparture;
    private final LocalDateTime plannedArrival;
    private final String sourceCity;
    private final String destinationCity;
    private final String sourceNationality;
    private final String destinationNationality;

    public FlightPublicRowDto(
            String flightNumber,
            int availablePassengersSeats,
            LocalDateTime plannedDeparture,
            LocalDateTime plannedArrival,
            String sourceCity,
            String destinationCity,
            String sourceNationality,
            String destinationNationality) {

        this.flightNumber = flightNumber;
        this.availablePassengersSeats = availablePassengersSeats;
        this.plannedDeparture = plannedDeparture;
        this.plannedArrival = plannedArrival;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.sourceNationality = sourceNationality;
        this.destinationNationality = destinationNationality;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getAvailablePassengersSeats() {
        return availablePassengersSeats;
    }

    public String getPlannedDeparture() {
        return DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss").format(plannedDeparture);
    }

    public String getPlannedArrival() {
        return DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss").format(plannedArrival);
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
}
