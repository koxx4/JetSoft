package org.jetsoft.web.jssystemapp.flight.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.flight.application.*;
import org.jetsoft.web.jssystemapp.flight.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class JpaReservationsQueries extends JpaQueries<Reservation> implements ReservationsQueries {

    private final FlightQueries flightQueries;

    @Autowired
    JpaReservationsQueries(EntityManager entityManager, FlightQueries flightQueries) {
        super(entityManager, Reservation.class);
        this.flightQueries = flightQueries;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDto> getAllReservationsDtoForCustomer(Long customerId) {

        return getAllCustomerReservations(customerId).stream()
                .map(this::toReservationDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationListRowDto> getAllReservationListRowsForCustomer(Long customerId) {

        return getAllCustomerReservations(customerId).stream()
                .map(this::toReservationListRowDto)
                .toList();
    }

    private ReservationDto toReservationDto(Reservation reservation) {

        String flightNumber = flightQueries.getFlightNumberByFlightId(reservation.getFlightId());

        return new ReservationDto(
                reservation.getReservationNumber(),
                flightNumber,
                reservation.getCreatedAt()
        );
    }

    private ReservationListRowDto toReservationListRowDto(Reservation reservation) {

        FlightCustomerRowDto flight = flightQueries.getFlightCustomerRowDto(reservation.getFlightId());

        return new ReservationListRowDto(
                reservation.getId(),
                reservation.getReservationNumber(),
                flight.flightNumber(),
                flight.sourceNationality(),
                flight.sourceCity(),
                flight.destinationNationality(),
                flight.destinationCity(),
                flight.plannedDeparture(),
                flight.plannedArrival(),
                reservation.getCreatedAt()
        );
    }

    private List<Reservation> getAllCustomerReservations(Long customerId) {

        var criteriaBuilder = getCriteriaBuilder();
        var criteriaQuery = getCriteriaQuery(criteriaBuilder);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get("customerId"), customerId));

        return getEntityManager().createQuery(criteriaQuery)
                .getResultList();
    }
}
