package org.jetsoft.web.jssystemapp.customer.application;

import java.time.LocalDateTime;

public record CustomerDetailsDto(
        String firstName,
        String lastName,
        String phone,
        String email,
        LocalDateTime registrationDate,
        int numberOfReservations
) {}
