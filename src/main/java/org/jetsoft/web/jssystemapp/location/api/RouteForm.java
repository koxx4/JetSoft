package org.jetsoft.web.jssystemapp.location.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RouteForm {

    private Long routeId;
    @NotNull(message = "Wybierz miasto")
    private Long sourceCityId;
    @NotNull(message = "Wybierz miasto")
    private Long destinationCityId;
    @Min(value = 0, message = "Trasa nie może mieć ujemnego dystansu")
    private Long distance;

    public Long getSourceCityId() {
        return sourceCityId;
    }

    public void setSourceCityId(Long sourceCityId) {
        this.sourceCityId = sourceCityId;
    }

    public Long getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(Long destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }
}
