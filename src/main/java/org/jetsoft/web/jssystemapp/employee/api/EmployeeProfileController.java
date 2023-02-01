package org.jetsoft.web.jssystemapp.employee.api;

import org.jetsoft.web.jssystemapp.employee.application.EmployeeAccountQueries;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeProfileDto;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeQueries;
import org.jetsoft.web.jssystemapp.employee.application.PilotQueries;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public
class EmployeeProfileController {

    private final EmployeeQueries employeeQueries;
    private final EmployeeAccountQueries employeeAccountQueries;
    private final PilotQueries pilotQueries;

    EmployeeProfileController(
            EmployeeQueries employeeQueries,
            EmployeeAccountQueries employeeAccountQueries,
            PilotQueries pilotQueries) {

        this.employeeQueries = employeeQueries;
        this.employeeAccountQueries = employeeAccountQueries;
        this.pilotQueries = pilotQueries;
    }

    @GetMapping("/employee/profileView")
    String getProfileView(Model model, Principal principal) {

        Long employeeId = employeeAccountQueries.getEmployeeAccountIdByUsername(principal.getName());
        EmployeeProfileDto employeeProfile = employeeQueries.getEmployeeProfileDto(employeeId);

        boolean isPilot = employeeQueries.isEmployeePilot(principal.getName());

        if (isPilot) {

            List<String> flightNumbersOfAssignedFlights = pilotQueries.getAllFlightNumbersAssignedToPilot(employeeId);

            model.addAttribute("flightNumbers", flightNumbersOfAssignedFlights);
        }

        model.addAttribute("profile", employeeProfile);
        model.addAttribute("isPilot", isPilot);

        return "employee-profile-view";
    }
}
