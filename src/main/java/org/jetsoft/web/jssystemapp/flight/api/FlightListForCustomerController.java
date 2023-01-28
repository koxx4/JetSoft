package org.jetsoft.web.jssystemapp.flight.api;

import org.jetsoft.web.jssystemapp.customer.application.CustomerQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightCustomerRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FlightListForCustomerController {

    private final FlightQueries flightQueries;
    private final CustomerQueries customerQueries;

    @Autowired
    FlightListForCustomerController(FlightQueries flightQueries, CustomerQueries customerQueries) {

        this.flightQueries = flightQueries;
        this.customerQueries = customerQueries;
    }

    @GetMapping("/customer/flightList")
    String listFlights(@RequestParam Long page, Model model, Principal principal) {

        if (page == null)
            page = 0L;

        Long customerId = customerQueries.getCustomerIdByCustomerEmail(principal.getName());

        List<FlightCustomerRowDto> flights = flightQueries.getFlightsForCustomersPaginated(page.intValue(), 10);
        Map<Long, Boolean> reservationsTruthTable = flights.stream()
                .map(flightCustomerRowDto -> Map.entry(flightCustomerRowDto.flightId(), customerQueries.hasReservationOnThisFlight(customerId, flightCustomerRowDto.flightNumber())))
                        .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));

        model.addAttribute("flights", flights);
        model.addAttribute("currentPage", page);
        model.addAttribute("reservationsTruthTable", reservationsTruthTable);

        return "flight-customer-list-view";
    }
}
