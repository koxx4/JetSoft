package org.jetsoft.web.jssystemapp.flight.application;

public interface ReservationNumberProvider {

    String generateReservationNumber(Long flightId, Long customerId);
}
