package org.jetsoft.web.jssystemapp.flight.application;

import org.jetsoft.web.jssystemapp.customer.application.CustomerQueries;
import org.jetsoft.web.jssystemapp.flight.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerQueries customerQueries;
    private final ReservationsQueries reservationsQueries;
    private final ReservationNumberProvider reservationNumberProvider;

    @Autowired
    ReservationService(ReservationRepository reservationRepository, CustomerQueries customerQueries, ReservationsQueries reservationsQueries, ReservationNumberProvider reservationNumberProvider) {
        this.reservationRepository = reservationRepository;
        this.customerQueries = customerQueries;
        this.reservationsQueries = reservationsQueries;
        this.reservationNumberProvider = reservationNumberProvider;
    }

    public String makeReservationAndGetReservationNumber(Long flightId, String userEmail) {

        Long customerId = customerQueries.getCustomerIdByCustomerEmail(userEmail);
        String reservationNumber = reservationNumberProvider.generateReservationNumber(flightId, customerId);

        Reservation newReservation = new Reservation(
                customerId,
                flightId,
                reservationNumber,
                LocalDateTime.now()
        );

        reservationRepository.save(newReservation);

        return reservationNumber;
    }

    public void removeCustomerReservation(Long reservationId, String customerEmail) {

        Long customerId = customerQueries.getCustomerIdByCustomerEmail(customerEmail);

        if (!reservationsQueries.isReservationAssignedToCustomer(reservationId, customerId)) {

            throw new IllegalArgumentException(
                    "Rezerwacja o id=" + reservationId + " nie jest przypisana do użytkownika " + customerEmail);
        }

        reservationRepository.remove(reservationId);
    }
}
