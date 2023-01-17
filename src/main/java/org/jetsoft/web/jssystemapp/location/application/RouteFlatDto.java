package org.jetsoft.web.jssystemapp.location.application;

public record RouteFlatDto(
        Long routeId,
        String sourceNationalityName,
        String sourceCityName,
        String destinationNationalityName,
        String destinationCityName) {}
