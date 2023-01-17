package org.jetsoft.web.jssystemapp.employee.api;

import org.jetsoft.web.jssystemapp.employee.application.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
class EmployeeListController {

    private final EmployeeQueries employeeQueries;

    @Autowired
    EmployeeListController(EmployeeQueries employeeQueries) {

        this.employeeQueries = employeeQueries;
    }

    @GetMapping("/employeeList")
    String getEmployeeList(Model model) {

        List<EmployeeBasicInfoDto> employeeBasicInfoDtoList = employeeQueries.getEmployeeBasicInfoDtoList();

        model.addAttribute("employeeList", employeeBasicInfoDtoList);

        return "employee-list-view";
    }
}
