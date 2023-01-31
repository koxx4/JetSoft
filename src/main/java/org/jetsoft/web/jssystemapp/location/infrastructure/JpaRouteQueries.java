package org.jetsoft.web.jssystemapp.location.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightRouteQueries;
import org.jetsoft.web.jssystemapp.location.application.*;
import org.jetsoft.web.jssystemapp.location.domain.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
class JpaRouteQueries extends JpaQueries<Route> implements RouteQueries {

    private final CityAndNationalityQueries cityAndNationalityQueries;
    private final FlightRouteQueries flightRouteQueries;

    @Autowired
    JpaRouteQueries(
            EntityManager entityManager,
            CityAndNationalityQueries cityAndNationalityQueries,
            FlightRouteQueries flightRouteQueries) {

        super(entityManager, Route.class);
        this.cityAndNationalityQueries = cityAndNationalityQueries;
        this.flightRouteQueries = flightRouteQueries;
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

    @Override
    public List<RouteListRowDto> getRouteListRowDtoList() {

        return getAll().stream()
                .map(this::toRouteListRowDto)
                .toList();
    }

    @Override
    public Long getRouteDistanceByRouteId(Long id) {
        return getById(id).getDistance();
    }

    private RouteFlatDto toRouteFlatDto(Route route) {

        NationalityAndCityDto source = cityAndNationalityQueries
                .getNationalityAndCityDtoByCityId(route.getSourceCity().getId());

        NationalityAndCityDto destination = cityAndNationalityQueries
                .getNationalityAndCityDtoByCityId(route.getDestinationCity().getId());

        return new RouteFlatDto(
                route.getId(),
                source.nationalityName(),
                source.cityName(),
                destination.nationalityName(),
                destination.cityName(),
                route.getDistance()
        );
    }

    private RouteListRowDto toRouteListRowDto(Route route) {

        NationalityAndCityDto source = cityAndNationalityQueries
                .getNationalityAndCityDtoByCityId(route.getSourceCity().getId());

        NationalityAndCityDto destination = cityAndNationalityQueries
                .getNationalityAndCityDtoByCityId(route.getDestinationCity().getId());

        int assignedFlightsCount = flightRouteQueries.getFlightsCountAssignedToRoute(route.getId());

        return new RouteListRowDto(
                route.getId(),
                source.nationalityName(),
                source.cityName(),
                destination.nationalityName(),
                destination.cityName(),
                route.getDistance(),
                assignedFlightsCount
        );
    }
}
