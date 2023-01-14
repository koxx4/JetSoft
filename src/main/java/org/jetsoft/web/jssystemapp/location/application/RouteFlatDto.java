package org.jetsoft.web.jssystemapp.location.application;

public class RouteFlatDto {

    private final String sourceNationalityName;
    private final String sourceCityName;
    private final String destinationNationalityName;
    private final String destinationCityName;

    public RouteFlatDto(String sourceNationalityName, String sourceCityName, String destinationNationalityName, String destinationCityName) {

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
}
