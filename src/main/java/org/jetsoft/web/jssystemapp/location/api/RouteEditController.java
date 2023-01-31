package org.jetsoft.web.jssystemapp.location.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.flight.application.FlightRouteQueries;
import org.jetsoft.web.jssystemapp.location.application.CityAndNationalityQueries;
import org.jetsoft.web.jssystemapp.location.application.NationalityAndCityWithIdDto;
import org.jetsoft.web.jssystemapp.location.application.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Controller
class RouteEditController {

    private final CityAndNationalityQueries cityAndNationalityQueries;
    private final FlightRouteQueries flightRouteQueries;
    private final RouteService routeService;

    @Autowired
    RouteEditController(
            CityAndNationalityQueries cityAndNationalityQueries,
            FlightRouteQueries flightRouteQueries,
            RouteService routeService) {


        this.cityAndNationalityQueries = cityAndNationalityQueries;
        this.flightRouteQueries = flightRouteQueries;
        this.routeService = routeService;
    }

    @ModelAttribute("cities")
    private List<NationalityAndCityWithIdDto> addCitiesToModel() {

        return cityAndNationalityQueries.getNationalityAndCityWithIdDtoList();
    }

    @GetMapping("/employee/editRoute")
    String getRouteListView(@RequestParam(required = false) Long id, Model model) {

        RouteForm routeForm = new RouteForm();

        if (id != null)
            routeService.fillRouteForm(routeForm, id);

        model.addAttribute("routeForm", routeForm);

        return "route-edit-view";
    }

    @PostMapping("/employee/saveRoute")
    String saveRoute(@ModelAttribute @Valid RouteForm routeForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "route-edit-view";
        }

        routeService.updateOrCreateRouteFromForm(routeForm);

        return "redirect:/employee/routeList";
    }

    @DeleteMapping("/employee/deleteRoute")
    String deleteRoute(@RequestParam Long routeId) {

        if (flightRouteQueries.hasRouteAssignedAnyFlights(routeId))
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);

        routeService.deleteRouteById(routeId);

        return "redirect:/employee/routeList";
    }
}
