package org.jetsoft.web.jssystemapp.location.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.flight.domain.Route;
import org.jetsoft.web.jssystemapp.location.application.RouteFlatDto;
import org.jetsoft.web.jssystemapp.location.application.RouteQueries;
import org.jetsoft.web.jssystemapp.location.domain.City;
import org.jetsoft.web.jssystemapp.location.domain.Nationality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
class JpaRouteQueries extends JpaQueries<Route> implements RouteQueries {

    @Autowired
    protected JpaRouteQueries(EntityManager entityManager) {

        super(entityManager, Route.class);
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

        City sourceCity = getById(City.class, route.getSourceCityId());
        City destinationCity = getById(City.class, route.getDestinationCityId());

        Nationality sourceNationality = getById(Nationality.class, sourceCity.getNationalityId());
        Nationality destinationNationality = getById(Nationality.class, destinationCity.getNationalityId());

        return new RouteFlatDto(
                route.getId(),
                sourceNationality.getName(),
                sourceCity.getName(),
                destinationNationality.getName(),
                destinationCity.getName()
        );
    }
}
