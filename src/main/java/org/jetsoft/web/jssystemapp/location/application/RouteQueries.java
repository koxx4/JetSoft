package org.jetsoft.web.jssystemapp.location.application;

import java.util.List;

public interface RouteQueries {

    RouteFlatDto getRouteFlatDtoByRouteId(Long id);
    List<RouteFlatDto> getRouteFlatDtoList();
    List<RouteListRowDto> getRouteListRowDtoList();
    Long getRouteDistanceByRouteId(Long id);
    boolean exists(Long routeId);
}
