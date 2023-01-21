package org.jetsoft.web.jssystemapp.flight.domain;

import jakarta.persistence.*;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flightId")
    private List<PilotToFlight> pilots;

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
            LocalDateTime plannedArrival,
            List<PilotToFlight> pilots) {

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
        this.pilots = pilots;
    }

    //Musi istnieć przez ORM-a
    private Flight() {}

    public void activate() {

        if (!canBeActivated()) {

            throw new IllegalArgumentException();
        }

        active = true;
    }

    public boolean canBeActivated() {

        return !isNull(vehicleId) && !isNull(routeId) && !archival;
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

    public List<PilotToFlight> getPilots() {
        return pilots;
    }

    public void setPilots(List<PilotToFlight> pilots) {
        this.pilots = pilots;
    }

    public void addPilot(PilotToFlight pilot) {

        if (isNull(pilots)) {

            pilots = new ArrayList<>();
        }

        pilots.add(pilot);
    }
}
