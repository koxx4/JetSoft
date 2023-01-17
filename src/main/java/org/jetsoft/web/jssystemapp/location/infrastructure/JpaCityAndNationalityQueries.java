package org.jetsoft.web.jssystemapp.location.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.location.application.CityAndNationalityQueries;
import org.jetsoft.web.jssystemapp.location.application.NationalityAndCityDto;
import org.jetsoft.web.jssystemapp.location.domain.City;
import org.jetsoft.web.jssystemapp.location.domain.Nationality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class JpaCityAndNationalityQueries extends JpaQueries<City> implements CityAndNationalityQueries {

    @Autowired
    JpaCityAndNationalityQueries(EntityManager entityManager) {
        super(entityManager, City.class);
    }

    @Override
    public NationalityAndCityDto getNationalityAndCityDtoByCityId(Long id) {

        City city = getById(id);

        if (city == null) {
            throw new EntityNotFoundException("No city found with id = " + id);
        }

        Nationality nationality = getById(Nationality.class, city.getNationalityId());

        return new NationalityAndCityDto(nationality.getName(), city.getName());
    }
}
