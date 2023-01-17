package org.jetsoft.web.jssystemapp.location.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.flight.domain.Route;
import org.jetsoft.web.jssystemapp.location.application.CityAndNationalityQueries;
import org.jetsoft.web.jssystemapp.location.application.NationalityAndCityDto;
import org.jetsoft.web.jssystemapp.location.application.RouteFlatDto;
import org.jetsoft.web.jssystemapp.location.application.RouteQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
class JpaRouteQueries extends JpaQueries<Route> implements RouteQueries {

    private final CityAndNationalityQueries cityAndNationalityQueries;

    @Autowired
    JpaRouteQueries(EntityManager entityManager, CityAndNationalityQueries cityAndNationalityQueries) {

        super(entityManager, Route.class);
        this.cityAndNationalityQueries = cityAndNationalityQueries;
    }

    @Override
    @Transactional
    public RouteFlatDto getRouteFlatDtoByRouteId(Long id) {

        Assert.notNull(id, "id must not be null");

        Optional<Route> optionalRoute = findById(id);

        return optionalRoute.map(this::toRouteFlatDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public List<RouteFlatDto> getRouteFlatDtoList() {

        return getAll().stream()
                .map(this::toRouteFlatDto)
                .toList();
    }

    private RouteFlatDto toRouteFlatDto(Route route) {

        NationalityAndCityDto source = cityAndNationalityQueries
                .getNationalityAndCityDtoByCityId(route.getSourceCityId());

        NationalityAndCityDto destination = cityAndNationalityQueries
                .getNationalityAndCityDtoByCityId(route.getDestinationCityId());

        return new RouteFlatDto(
                route.getId(),
                source.nationalityName(),
                source.cityName(),
                destination.nationalityName(),
                destination.cityName()
        );
    }
}
