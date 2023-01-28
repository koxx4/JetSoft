package org.jetsoft.web.jssystemapp.customer.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetsoft.web.jssystemapp.core.AbstractEntityWithGeneratedId;

@Entity
@Table(schema = "ppd")
@Access(AccessType.FIELD)
public class Passenger extends AbstractEntityWithGeneratedId {

    private String firstName;
    private String lastName;
    private boolean adult;
    private Long reservationId;
    private Long nationalityId;

    private Passenger() {}

    public Passenger(String firstName, String lastName, boolean adult, Long reservationId, Long nationalityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adult = adult;
        this.reservationId = reservationId;
        this.nationalityId = nationalityId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }
}
