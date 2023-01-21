package org.jetsoft.web.jssystemapp.customer.application;

public record CustomerNameAndEmailDto(
        String firstName,
        String lastName,
        String email
) {}
