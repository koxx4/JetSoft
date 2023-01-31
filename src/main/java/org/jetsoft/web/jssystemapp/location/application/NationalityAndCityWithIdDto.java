package org.jetsoft.web.jssystemapp.location.application;

public record NationalityAndCityWithIdDto(
        Long cityId,
        String nationalityName,
        String cityName) {}