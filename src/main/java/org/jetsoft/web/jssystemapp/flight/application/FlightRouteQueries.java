package org.jetsoft.web.jssystemapp.flight.application;

public interface FlightRouteQueries {

    int getFlightsCountAssignedToRoute(Long routeId);
    boolean hasRouteAssignedAnyFlights(Long routeId);
}
