package org.jetsoft.web.jssystemapp.flight.api;

import org.jetsoft.web.jssystemapp.flight.application.FlightEmployeeRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
class FlightListController {

    private final FlightQueries flightQueries;

    @Autowired
    FlightListController(FlightQueries flightQueries) {

        this.flightQueries = flightQueries;
    }

    @GetMapping("/flightList")
    String listFlights(Model model) {

        List<FlightEmployeeRowDto> flights = flightQueries.getFlightEmployeeRowDtoListPaginated(0, 10);

        model.addAttribute("flights", flights);

        return "flight-list-view";
    }
}