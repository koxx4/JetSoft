package org.jetsoft.web.jssystemapp.flight.api;

import org.jetsoft.web.jssystemapp.flight.application.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
class CustomerFlightReservationController {

    private final ReservationService reservationService;

    CustomerFlightReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/customer/makeReservation")
    String handleCustomerReservation(@RequestParam Long flightId, Model model, Principal principal) {

        String userEmail = principal.getName();

        String reservationNumber = reservationService.makeReservationAndGetReservationNumber(flightId, userEmail);

        model.addAttribute("reservationNumber", reservationNumber);

        return "customer/customer-reservation-success-view";
    }
}
