package org.jetsoft.web.jssystemapp.flight.application;

import java.util.List;

public interface ReservationsQueries {

    List<ReservationDto> getAllReservationsDtoForCustomer(Long customerId);

    List<ReservationListRowDto> getAllReservationListRowsForCustomer(Long customerId);

    int getReservationCountForCustomer(Long customerId);

    int getReservationCountForFlight(Long flightId);

    boolean isReservationAssignedToCustomer(Long reservationId, Long customerId);

    String getReservationNumberById(Long reservationId);
}
