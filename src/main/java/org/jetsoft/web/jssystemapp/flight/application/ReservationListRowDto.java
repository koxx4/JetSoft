package org.jetsoft.web.jssystemapp.flight.application;

import java.time.LocalDateTime;

public record ReservationListRowDto(
        Long reservationId,
        String reservationNumber,
        String flightNumber,
        String sourceNationality,
        String sourceCity,
        String destinationNationality,
        String destinationCity,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        LocalDateTime reservationDate
) {}
