package org.jetsoft.web.jssystemapp.location.application;

public class RouteFlatDto {

    private final Long routeId;
    private final String sourceNationalityName;
    private final String sourceCityName;
    private final String destinationNationalityName;
    private final String destinationCityName;

    public RouteFlatDto(Long routeId, String sourceNationalityName, String sourceCityName, String destinationNationalityName, String destinationCityName) {

        this.routeId = routeId;
        this.sourceNationalityName = sourceNationalityName;
        this.sourceCityName = sourceCityName;
        this.destinationNationalityName = destinationNationalityName;
        this.destinationCityName = destinationCityName;
    }

    public String getSourceNationalityName() {
        return sourceNationalityName;
    }

    public String getSourceCityName() {
        return sourceCityName;
    }

    public String getDestinationNationalityName() {
        return destinationNationalityName;
    }

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public Long getRouteId() {
        return routeId;
    }
}
