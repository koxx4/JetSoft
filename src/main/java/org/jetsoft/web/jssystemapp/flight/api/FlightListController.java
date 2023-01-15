package org.jetsoft.web.jssystemapp.flight.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.flight.application.FlightPublicRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
class FlightListController {

    private final FlightQueries flightQueries;
    private final FlightService flightService;
    private final FlightFormValidator flightFormValidator;

    @Autowired
    FlightListController(
            FlightQueries flightQueries,
            FlightService flightService,
            FlightFormValidator flightFormValidator) {

        this.flightQueries = flightQueries;
        this.flightService = flightService;
        this.flightFormValidator = flightFormValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(flightFormValidator);
    }

    @GetMapping("/flightList")
    String listFlights(Model model) {

        List<FlightPublicRowDto> flights = flightQueries.getFlightPublicRowDtoListPaginated(0, 10);

        model.addAttribute("flights", flights);

        return "flight-list-view";
    }

    @GetMapping("/editFlight")
    String saveFlight(@RequestParam(required = false) Long id, Model model) {

        if (id == null) {

            model.addAttribute("flightForm", new FlightForm());

            return "flight-edit-view";
        }

        var filledForm = flightService.getFilledFormFromFlight(id);

        model.addAttribute("flightForm", filledForm);

        return "flight-edit-view";
    }

    @PostMapping("/addFlight")
    String saveFlight(@ModelAttribute @Valid FlightForm flightForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "flight-edit-view";
        }

        flightService.saveFlightFromForm(flightForm);

        return "redirect:/flightList";
    }

}