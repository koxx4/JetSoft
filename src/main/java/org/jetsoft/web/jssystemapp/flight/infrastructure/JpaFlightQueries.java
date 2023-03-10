package org.jetsoft.web.jssystemapp.flight.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Root;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightCustomerRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightEmployeeRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.jetsoft.web.jssystemapp.flight.domain.Flight;
import org.jetsoft.web.jssystemapp.flight.domain.Reservation;
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
    public FlightEmployeeRowDto getFlightEmployeeRowDtoByFlightId(Long id) {

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
    public List<FlightEmployeeRowDto> getFlightEmployeeRowDtoListPaginated(int page, int pageSize) {

        return getAllPaginated(page, pageSize).stream()
                .map(flight -> {

                    var routeFlatDto = routeQueries.getRouteFlatDtoByRouteId(flight.getRouteId());

                    return toFlightPublicRowDto(flight, routeFlatDto);
                })
                .toList();
    }

    @Override
    public List<FlightEmployeeRowDto> getFlightEmployeeRowDtoList() {

        return getAll().stream()
                .map(flight -> {

                    var routeFlatDto = routeQueries.getRouteFlatDtoByRouteId(flight.getRouteId());

                    return toFlightPublicRowDto(flight, routeFlatDto);
                })
                .toList();
    }

    @Override
    public List<FlightEmployeeRowDto> getFlightEmployeeRowDtoListFilteredByIdList(List<Long> flightIdList) {

        return getFlightEmployeeRowDtoList().stream()
                .filter(dto -> flightIdList.contains(dto.getFlightId()))
                .toList();
    }

    @Override
    public List<FlightEmployeeRowDto> getFlightEmployeeRowDtoListWithout(List<Long> flightIdList) {

        return getFlightEmployeeRowDtoList().stream()
                .filter(dto -> !flightIdList.contains(dto.getFlightId()))
                .toList();
    }

    @Override
    public boolean exists(Long id) {

        return super.exists(id);
    }

    @Override
    public List<FlightCustomerRowDto> getFlightsForCustomersPaginated(int page, int i) {

        return getAllPaginated(page, i).stream()
                .filter(Flight::isVisibleForCustomers)
                .map(this::toFlightCustomerRowDto)
                .toList();
    }

    @Override
    public FlightCustomerRowDto getFlightCustomerRowDto(Long flightId) {

        return findById(flightId)
                .map(this::toFlightCustomerRowDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public String getFlightNumberByFlightId(Long id) {

        return findById(id)
                .map(Flight::getFlightNumber)
                .orElseThrow(EntityNotFoundException::new);
    }

    private FlightCustomerRowDto toFlightCustomerRowDto(Flight flight) {

        var routeFlatDto = routeQueries.getRouteFlatDtoByRouteId(flight.getRouteId());
        var reservationCount = getReservationCountForFlight(flight.getId());

        return new FlightCustomerRowDto(
                flight.getId(),
                flight.getFlightNumber(),
                (long) flight.getAvailablePassengersSeats() - reservationCount,
                flight.getPlannedDeparture(),
                flight.getPlannedArrival(),
                routeFlatDto.sourceCityName(),
                routeFlatDto.destinationCityName(),
                routeFlatDto.sourceNationalityName(),
                routeFlatDto.destinationNationalityName()
        );
    }

    private FlightEmployeeRowDto toFlightPublicRowDto(Flight flight, RouteFlatDto routeFlatDto) {

        var reservationCount = getReservationCountForFlight(flight.getId());

        return new FlightEmployeeRowDto(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getAvailablePassengersSeats(),
                flight.getAvailablePassengersSeats()- reservationCount,
                flight.getPlannedDeparture(),
                flight.getPlannedArrival(),
                routeFlatDto.sourceCityName(),
                routeFlatDto.destinationCityName(),
                routeFlatDto.sourceNationalityName(),
                routeFlatDto.destinationNationalityName(),
                flight.isActive(),
                flight.getLastModificationDate()
        );
    }

    private int getReservationCountForFlight(Long flightId) {

        var criteriaBuilder = getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get("flightId"), flightId));

        return getEntityManager().createQuery(criteriaQuery)
                .getResultList().size();
    }
}
