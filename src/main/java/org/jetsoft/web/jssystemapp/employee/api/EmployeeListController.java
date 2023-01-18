package org.jetsoft.web.jssystemapp.employee.api;

import org.jetsoft.web.jssystemapp.employee.application.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
class EmployeeListController {

    private final EmployeeService employeeService;

    @Autowired
    EmployeeListController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @ModelAttribute("employeeFilterForm")
    private EmployeeFilterForm addEmployeeFilterFormToModel() {

        return new EmployeeFilterForm();
    }

    @GetMapping("/employeeList")
    String getEmployeeList(@ModelAttribute EmployeeFilterForm filterForm, Model model) {

        List<EmployeeBasicInfoDto> employeeBasicInfoDtoList = employeeService
                .getEmployeeBasicInfoDtoListFiltered(filterForm);

        model.addAttribute("employeeList", employeeBasicInfoDtoList);

        return "employee-list-view";
    }
}
