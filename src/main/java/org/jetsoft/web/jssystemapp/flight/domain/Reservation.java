package org.jetsoft.web.jssystemapp.flight.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

import java.time.LocalDateTime;

@Entity
@Table(schema = "data")
@Access(AccessType.FIELD)
public class Reservation extends AbstractEntityWithGeneratedId {

    private Long customerId;
    private Long flightId;
    private String reservationNumber;
    private LocalDateTime createdAt;

    private Reservation() {}

    public Reservation(Long customerId, Long flightId, String reservationNumber, LocalDateTime createdAt) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.reservationNumber = reservationNumber;
        this.createdAt = createdAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
