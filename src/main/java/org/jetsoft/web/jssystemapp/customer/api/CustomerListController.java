package org.jetsoft.web.jssystemapp.customer.api;

import org.jetsoft.web.jssystemapp.customer.application.CustomerQueries;
import org.jetsoft.web.jssystemapp.flight.application.ReservationListRowDto;
import org.jetsoft.web.jssystemapp.flight.application.ReservationsQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
class CustomerListController {

    private final ReservationsQueries reservationsQueries;
    private final CustomerQueries customerQueries;

    @Autowired
    CustomerListController(ReservationsQueries reservationsQueries, CustomerQueries customerQueries) {
        this.reservationsQueries = reservationsQueries;
        this.customerQueries = customerQueries;
    }

    @GetMapping("/customer/reservations")
    String listAllCustomerReservations(Model model, Principal principal) {

        String customerEmail = principal.getName();
        Long customerId = customerQueries.getCustomerIdByCustomerEmail(customerEmail);
        List<ReservationListRowDto> reservationList = reservationsQueries.getAllReservationListRowsForCustomer(customerId);

        model.addAttribute("reservations", reservationList);

        return "customer-reservation-list-view";
    }
}
