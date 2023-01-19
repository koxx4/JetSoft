package org.jetsoft.web.jssystemapp.flight.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Entity
@Table(schema = "data")
@Access(AccessType.FIELD)
public class Flight extends AbstractEntityWithGeneratedId {

    private Long routeId;
    private Long vehicleId;
    private Long generatedFromScheduleId;
    private boolean active;
    private String flightNumber;
    private int availablePassengersSeats;
    private int minPilotCount;
    private boolean confidential;
    private boolean archival;
    private LocalDateTime lastModificationDate;
    private LocalDateTime plannedDeparture;
    private LocalDateTime plannedArrival;

    public Flight(
            Long routeId,
            Long vehicleId,
            Long generatedFromScheduleId,
            boolean active,
            String flightNumber,
            int availablePassengersSeats,
            int minPilotCount,
            boolean confidential,
            boolean archival,
            LocalDateTime lastModificationDate,
            LocalDateTime plannedDeparture,
            LocalDateTime plannedArrival) {

        this.routeId = routeId;
        this.vehicleId = vehicleId;
        this.generatedFromScheduleId = generatedFromScheduleId;
        this.active = active;
        this.flightNumber = flightNumber;
        this.availablePassengersSeats = availablePassengersSeats;
        this.minPilotCount = minPilotCount;
        this.confidential = confidential;
        this.archival = archival;
        this.lastModificationDate = lastModificationDate;
        this.plannedDeparture = plannedDeparture;
        this.plannedArrival = plannedArrival;
    }

    //Musi istnieć przez ORM-a
    private Flight() {}

    void activate() {

        if (isNull(vehicleId) || isNull(routeId) || archival) {

            throw new IllegalArgumentException();
        }

        active = true;
    }

    public Long getRouteId() {
        return routeId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public Long getGeneratedFromScheduleId() {
        return generatedFromScheduleId;
    }

    public boolean isActive() {
        return active;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getAvailablePassengersSeats() {
        return availablePassengersSeats;
    }

    public int getMinPilotCount() {
        return minPilotCount;
    }

    public boolean isConfidential() {
        return confidential;
    }

    public boolean isVisibleForCustomers() {

        return !isConfidential() && isActive() && !isArchival();
    }

    public boolean isArchival() {
        return archival;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public LocalDateTime getPlannedDeparture() {
        return plannedDeparture;
    }

    public LocalDateTime getPlannedArrival() {
        return plannedArrival;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setGeneratedFromScheduleId(Long generatedFromScheduleId) {
        this.generatedFromScheduleId = generatedFromScheduleId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setAvailablePassengersSeats(int availablePassengersSeats) {
        this.availablePassengersSeats = availablePassengersSeats;
    }

    public void setMinPilotCount(int minPilotCount) {
        this.minPilotCount = minPilotCount;
    }

    public void setConfidential(boolean confidential) {
        this.confidential = confidential;
    }

    public void setArchival(boolean archival) {
        this.archival = archival;
    }

    public void setLastModificationDate(LocalDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public void setPlannedDeparture(LocalDateTime plannedDeparture) {
        this.plannedDeparture = plannedDeparture;
    }

    public void setPlannedArrival(LocalDateTime plannedArrival) {
        this.plannedArrival = plannedArrival;
    }
}
