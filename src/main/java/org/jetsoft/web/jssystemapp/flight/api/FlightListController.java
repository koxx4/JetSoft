package org.jetsoft.web.jssystemapp.flight.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
class FlightListController {

    @GetMapping("/flightList")
    String listFlights(Model model) {

        String formattedDate = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());

        model.addAttribute("serverTime", formattedDate);

        return "flight-list";
    }
}