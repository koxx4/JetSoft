package org.jetsoft.web.jssystemapp.flight.api;

import org.jetsoft.web.jssystemapp.flight.application.FlightPublicRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        String formattedDate = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        List<FlightPublicRowDto> flights = flightQueries.getFlightPublicRowDtoListPaginated(0, 10);

        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("flights", flights);

        return "flight-list";
    }
}