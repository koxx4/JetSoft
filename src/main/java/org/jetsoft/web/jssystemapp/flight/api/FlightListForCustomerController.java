package org.jetsoft.web.jssystemapp.flight.api;

import org.jetsoft.web.jssystemapp.flight.application.FlightCustomerRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FlightListForCustomerController {

    private final FlightQueries flightQueries;

    @Autowired
    FlightListForCustomerController(FlightQueries flightQueries) {

        this.flightQueries = flightQueries;
    }

    @GetMapping("/customer/flightList")
    String listFlights(@RequestParam Long page, Model model) {

        if (page == null)
            page = 0L;

        List<FlightCustomerRowDto> flights = flightQueries.getFlightsForCustomersPaginated(page.intValue(), 10);

        model.addAttribute("flights", flights);
        model.addAttribute("currentPage", page);

        return "flight-customer-list-view";
    }
}
