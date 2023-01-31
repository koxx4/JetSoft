package org.jetsoft.web.jssystemapp.location.application;

public record RouteListRowDto(
        Long routeId,
        String sourceNationalityName,
        String sourceCityName,
        String destinationNationalityName,
        String destinationCityName,
        Long distance,
        int flightsAssignedCount
) {}
