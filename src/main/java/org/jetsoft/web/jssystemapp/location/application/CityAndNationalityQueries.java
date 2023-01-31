package org.jetsoft.web.jssystemapp.location.application;

import java.util.List;

public interface CityAndNationalityQueries {

    NationalityAndCityDto getNationalityAndCityDtoByCityId(Long id);

    String getNationalityNameByNationalityId(Long id);

    List<NationalityDto> getNationalityDtoList();
}
