package org.jetsoft.web.jssystemapp.customer.api;

import org.jetsoft.web.jssystemapp.flight.application.ReservationService;
import org.jetsoft.web.jssystemapp.flight.application.ReservationsQueries;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
class CustomerReservationEditController {

    private static final String V_CUSTOMER_RESERVATION_REMOVE_SUCCESS = "customer/customer-reservation-remove-success-view";
    private final ReservationService reservationService;
    private final ReservationsQueries reservationsQueries;


    CustomerReservationEditController(ReservationService reservationService, ReservationsQueries reservationsQueries) {

        this.reservationService = reservationService;
        this.reservationsQueries = reservationsQueries;
    }


    @DeleteMapping("/customer/removeReservation")
    String listAllCustomerReservations(
            @RequestParam Long reservationId,
            Model model,
            Principal principal) {

        String reservationNumber = reservationsQueries.getReservationNumberById(reservationId);
        String customerEmail = principal.getName();
        reservationService.removeCustomerReservation(reservationId, customerEmail);

        model.addAttribute("reservationNumber", reservationNumber);

        return V_CUSTOMER_RESERVATION_REMOVE_SUCCESS;
    }
}
