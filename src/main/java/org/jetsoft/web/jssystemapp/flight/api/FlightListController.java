package org.jetsoft.web.jssystemapp.flight.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.flight.application.FlightEmployeeRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightService;
import org.jetsoft.web.jssystemapp.location.application.RouteQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
class FlightListController {

    private final FlightQueries flightQueries;
    private final RouteQueries routeQueries;
    private final VehicleQueries vehicleQueries;
    private final FlightService flightService;
    private final FlightFormValidator flightFormValidator;

    @Autowired
    FlightListController(
            FlightQueries flightQueries,
            RouteQueries routeQueries,
            VehicleQueries vehicleQueries,
            FlightService flightService,
            FlightFormValidator flightFormValidator) {

        this.flightQueries = flightQueries;
        this.routeQueries = routeQueries;
        this.vehicleQueries = vehicleQueries;
        this.flightService = flightService;
        this.flightFormValidator = flightFormValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(flightFormValidator);
    }

    @GetMapping("/flightList")
    String listFlights(Model model) {

        List<FlightEmployeeRowDto> flights = flightQueries.getFlightPublicRowDtoListPaginated(0, 10);

        model.addAttribute("flights", flights);

        return "flight-list-view";
    }

    @GetMapping("/editFlight")
    String saveFlight(@RequestParam(required = false) Long id, Model model) {

        addRouteFlatDtoList(model);

        if (id == null) {

            model.addAttribute("flightForm", new FlightForm());

            return "flight-edit-view";
        }

        var filledForm = flightService.getFilledFormFromFlight(id);

        model.addAttribute("flightForm", filledForm);

        return "flight-edit-view";
    }

    private void addRouteFlatDtoList(Model model) {

        var routeList = routeQueries.getRouteFlatDtoList();
        var vehicleList = vehicleQueries.getVehicleInFlightFormDtoList();

        model.addAttribute("routeList", routeList);
        model.addAttribute("vehicleList", vehicleList);
    }

    @PostMapping("/addFlight")
    String saveFlight(@ModelAttribute @Valid FlightForm flightForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "flight-edit-view";
        }

        flightService.saveFlightFromForm(flightForm);

        return "redirect:/flightList";
    }

    @DeleteMapping("/deleteFlight")
    String deleteFlight(@RequestParam Long deleteId,  Model model) {

        Assert.notNull(deleteId, "id cannot be null!");

        flightService.removeFlightById(deleteId);

        model.addAttribute("deleted", true);

        return "redirect:/flightList";
    }
}