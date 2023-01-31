package org.jetsoft.web.jssystemapp.employee.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.employee.application.PilotService;
import org.jetsoft.web.jssystemapp.employee.application.PilotStatusDto;
import org.jetsoft.web.jssystemapp.employee.application.PilotStatusQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightEmployeeRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.jetsoft.web.jssystemapp.location.application.CityAndNationalityQueries;
import org.jetsoft.web.jssystemapp.location.application.NationalityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
class PilotEditController {

    private static final String V_PILOT_EDIT = "pilot-edit-view";


    private final PilotStatusQueries pilotStatusQueries;
    private final FlightQueries flightQueries;
    private final CityAndNationalityQueries cityAndNationalityQueries;
    private final PilotService pilotService;

    @Autowired
    PilotEditController(
            PilotStatusQueries pilotStatusQueries,
            FlightQueries flightQueries,
            CityAndNationalityQueries cityAndNationalityQueries,
            PilotService pilotService) {

        this.pilotStatusQueries = pilotStatusQueries;
        this.flightQueries = flightQueries;
        this.cityAndNationalityQueries = cityAndNationalityQueries;
        this.pilotService = pilotService;
    }

    @ModelAttribute("statuses")
    private List<PilotStatusDto> addStatusListToModel() {

        return pilotStatusQueries.getStatusList();
    }

    @ModelAttribute("nationalities")
    private List<NationalityDto> addNationalityListToModel() {

        return cityAndNationalityQueries.getNationalityDtoList();
    }

    @GetMapping("/employee/editPilot")
    String getPilotList(
            @RequestParam Long id,
            Model model) {

        PilotForm pilotForm = new PilotForm();

        pilotService.fillForm(pilotForm, id);

        List<FlightEmployeeRowDto> flightPilotCanBeAssignedTo = flightQueries.getFlightEmployeeRowDtoListWithout(
                pilotForm.getAssignedFlights().stream().map(FlightEmployeeRowDto::getFlightId).toList());

        model.addAttribute("pilotForm", pilotForm);
        model.addAttribute("flightsToAssign", flightPilotCanBeAssignedTo);

        return V_PILOT_EDIT;
    }

    @PostMapping("/employee/savePilot")
    String savePilotForm(@ModelAttribute @Valid PilotForm pilotForm) {

        //save

        return "redirect:/employee/pilotList";
    }

    @PostMapping("/employee/assignToFlight")
    String assignPilotToFLight(@RequestParam Long flightId, @RequestParam Long pilotId, Principal principal) {

        //add flight
        pilotService.assignPilotToFlight(pilotId, flightId, principal.getName());

        return "redirect:/employee/editPilot?id=" + pilotId;
    }

    @DeleteMapping("/employee/removeFromFlight")
    String removePilotFromFlight(@RequestParam Long flightId, @RequestParam Long pilotId) {

        //remove flight
        pilotService.removePilotFromFlight(pilotId, flightId);

        return "redirect:/employee/editPilot?id=" + pilotId;
    }

}
