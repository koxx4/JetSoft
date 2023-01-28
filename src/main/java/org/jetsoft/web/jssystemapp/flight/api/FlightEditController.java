package org.jetsoft.web.jssystemapp.flight.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.employee.application.PilotDto;
import org.jetsoft.web.jssystemapp.employee.application.PilotQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightService;
import org.jetsoft.web.jssystemapp.location.application.RouteFlatDto;
import org.jetsoft.web.jssystemapp.location.application.RouteQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleInFlightFormDto;
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
class FlightEditController {

    private final RouteQueries routeQueries;
    private final VehicleQueries vehicleQueries;
    private final PilotQueries pilotQueries;
    private final FlightService flightService;
    private final FlightFormValidator flightFormValidator;

    @Autowired
    FlightEditController(
            RouteQueries routeQueries,
            VehicleQueries vehicleQueries,
            PilotQueries pilotQueries,
            FlightService flightService,
            FlightFormValidator flightFormValidator) {

        this.routeQueries = routeQueries;
        this.vehicleQueries = vehicleQueries;
        this.pilotQueries = pilotQueries;
        this.flightService = flightService;
        this.flightFormValidator = flightFormValidator;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {

        binder.addValidators(flightFormValidator);
    }

    @ModelAttribute("routeList")
    private List<RouteFlatDto> addRouteFlatDtoListToModel() {

        return routeQueries.getRouteFlatDtoList();
    }

    @ModelAttribute("vehicleList")
    private List<VehicleInFlightFormDto> addVehicleInFlightFormDtoListToModel() {

        return vehicleQueries.getVehicleInFlightFormDtoList();
    }

    @GetMapping("/employee/editFlight")
    String saveFlight(@RequestParam(required = false) Long id, Model model) {

        if (id == null) {

            model.addAttribute("flightForm", new FlightForm());

            return "flight-edit-view";
        }

        FlightForm filledForm = flightService.getFilledFormFromFlight(id);
        List<PilotDto> dataOfCurrentlyAssignedPilots = pilotQueries.getAllPilotsAssignedToFlight(id);

        model.addAttribute("flightForm", filledForm);
        model.addAttribute("dataOfCurrentlyAssignedPilots", dataOfCurrentlyAssignedPilots);

        return "flight-edit-view";
    }

    @PostMapping("/employee/addFlight")
    String saveFlight(@ModelAttribute @Valid FlightForm flightForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "flight-edit-view";
        }

        flightService.saveFlightFromForm(flightForm);

        return "redirect:/employee/flightList";
    }

    @DeleteMapping("/employee/deleteFlight")
    String deleteFlight(@RequestParam Long deleteId,  Model model) {

        Assert.notNull(deleteId, "id cannot be null!");

        flightService.removeFlightById(deleteId);

        model.addAttribute("deleted", true);

        return "redirect:/employee/flightList";
    }
}
