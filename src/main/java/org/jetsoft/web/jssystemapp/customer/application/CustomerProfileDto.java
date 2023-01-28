package org.jetsoft.web.jssystemapp.customer.application;

import java.time.LocalDateTime;

public record CustomerProfileDto(
        LocalDateTime registrationDate,
        int numberOfReservations
) {}
