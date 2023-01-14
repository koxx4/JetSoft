package org.jetsoft.web.jssystemapp.flight.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jetsoft.web.jssystemapp.flight.domain.Flight;
import org.jetsoft.web.jssystemapp.flight.domain.Route;
import org.jetsoft.web.jssystemapp.location.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class FlightQueries {

    private final EntityManager entityManager;

    @Autowired
    FlightQueries(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public FlightPublicRowDto getFlightPublicRowDtoByFlightId(Long id) {

        Assert.notNull(id, "id must not be null");

        var optionalFlight = Optional.ofNullable(entityManager.find(Flight.class, id));

        return optionalFlight
                .map(flight -> {

                    Route route = entityManager.find(Route.class, flight.getRouteId());
                    City sourceCity = entityManager.find(City.class, route.getSourceCityId());
                    City destinationCity = entityManager.find(City.class, route.getDestinationCityId());

                    return toFlightPublicRowDto(flight, sourceCity, destinationCity);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<FlightPublicRowDto> getFlightPublicRowDtoList() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> criteriaQuery = criteriaBuilder.createQuery(Flight.class);
        Root<Flight> flightRoot = criteriaQuery.from(Flight.class);

        return entityManager.createQuery(criteriaQuery).setMaxResults(10).getResultList().stream()
                .map(flight -> {

                    Route route = entityManager.find(Route.class, flight.getRouteId());
                    City sourceCity = entityManager.find(City.class, route.getSourceCityId());
                    City destinationCity = entityManager.find(City.class, route.getDestinationCityId());

                    return toFlightPublicRowDto(flight, sourceCity, destinationCity);
                }).toList();
    }

    private FlightPublicRowDto toFlightPublicRowDto(Flight flight, City sourceCity, City destinationCity) {

        return new FlightPublicRowDto(
                flight.getFlightNumber(),
                flight.getAvailablePassengersSeats(),
                flight.getPlannedDeparture(),
                flight.getPlannedArrival(),
                sourceCity.getName(),
                destinationCity.getName()
        );
    }
}
