package org.jetsoft.web.jssystemapp.flight.api;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class FlightForm {

    private Long id;
    private Long routeId;
    private Long vehicleId;
    private boolean active;
    @NotBlank(message = "Kod lotu nie może być pusty")
    private String flightNumber;
    private int availablePassengersSeats;
    private int minPilotCount;
    private boolean confidential;
    private boolean archival;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime plannedDeparture;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime plannedArrival;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getAvailablePassengersSeats() {
        return availablePassengersSeats;
    }

    public void setAvailablePassengersSeats(int availablePassengersSeats) {
        this.availablePassengersSeats = availablePassengersSeats;
    }

    public int getMinPilotCount() {
        return minPilotCount;
    }

    public void setMinPilotCount(int minPilotCount) {
        this.minPilotCount = minPilotCount;
    }

    public boolean isConfidential() {
        return confidential;
    }

    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }

    public boolean isArchival() {
        return archival;
    }

    public void setArchival(boolean archival) {
        this.archival = archival;
    }

    public LocalDateTime getPlannedDeparture() {
        return plannedDeparture;
    }

    public void setPlannedDeparture(LocalDateTime plannedDeparture) {
        this.plannedDeparture = plannedDeparture;
    }

    public LocalDateTime getPlannedArrival() {
        return plannedArrival;
    }

    public void setPlannedArrival(LocalDateTime plannedArrival) {
        this.plannedArrival = plannedArrival;
    }
}
