package org.jetsoft.web.jssystemapp.employee.api;

import org.jetsoft.web.jssystemapp.employee.application.EmployeeAccountQueries;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeProfileDto;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeQueries;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
class EmployeeProfileController {

    private final EmployeeQueries employeeQueries;
    private final EmployeeAccountQueries employeeAccountQueries;

    public EmployeeProfileController(EmployeeQueries employeeQueries, EmployeeAccountQueries employeeAccountQueries) {
        this.employeeQueries = employeeQueries;
        this.employeeAccountQueries = employeeAccountQueries;
    }

    @GetMapping("/employee/profileView")
    String getProfileView(Model model, Principal principal) {

        Long employeeId = employeeAccountQueries.getEmployeeAccountIdByUsername(principal.getName());
        EmployeeProfileDto employeeProfile = employeeQueries.getEmployeeProfileDto(employeeId);

        model.addAttribute("profile", employeeProfile);

        return "employee-profile-view";
    }
}
