package org.jetsoft.web.jssystemapp.flight.application;

import java.time.LocalDateTime;

public record ReservationDto(
        String reservationNumber,
        String flightNumber,
        LocalDateTime reservationDate
) {}
