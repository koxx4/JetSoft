package org.jetsoft.web.jssystemapp.flight.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightPublicRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.jetsoft.web.jssystemapp.flight.domain.Flight;
import org.jetsoft.web.jssystemapp.location.application.RouteFlatDto;
import org.jetsoft.web.jssystemapp.location.application.RouteQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
class JpaFlightQueries extends JpaQueries<Flight> implements FlightQueries {

    private final RouteQueries routeQueries;

    @Autowired
    JpaFlightQueries(EntityManager entityManager, RouteQueries routeQueries) {

        super(entityManager, Flight.class);

        this.routeQueries = routeQueries;
    }

    @Override
    @Transactional(readOnly = true)
    public FlightPublicRowDto getFlightPublicRowDtoByFlightId(Long id) {

        Assert.notNull(id, "id must not be null");

        Optional<Flight> optionalFlight = findById(id);

        return optionalFlight
                .map(flight -> {

                    var routeFlatDto = routeQueries.getRouteFlatDtoByRouteId(flight.getRouteId());

                    return toFlightPublicRowDto(flight, routeFlatDto);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightPublicRowDto> getFlightPublicRowDtoListPaginated(int page, int pageSize) {

        return getAllPaginated(page, pageSize).stream()
                .map(flight -> {

                    var routeFlatDto = routeQueries.getRouteFlatDtoByRouteId(flight.getRouteId());

                    return toFlightPublicRowDto(flight, routeFlatDto);
                })
                .toList();
    }

    @Override
    public boolean exists(Long id) {

        return super.exists(id);
    }

    private FlightPublicRowDto toFlightPublicRowDto(Flight flight, RouteFlatDto routeFlatDto) {

        return new FlightPublicRowDto(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getAvailablePassengersSeats(),
                flight.getPlannedDeparture(),
                flight.getPlannedArrival(),
                routeFlatDto.getSourceCityName(),
                routeFlatDto.getDestinationCityName(),
                routeFlatDto.getSourceNationalityName(),
                routeFlatDto.getDestinationNationalityName()
        );
    }
}
