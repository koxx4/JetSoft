package org.jetsoft.web.jssystemapp.employee.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.employee.application.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
class EmployeeListController {

    private final EmployeeQueries employeeQueries;
    private final EmployeeAccountQueries employeeAccountQueries;
    private final EmployeeRoleQueries employeeRoleQueries;
    private final EmployeeService employeeService;
    private final EmployeeFormValidator employeeFormValidator;

    @Autowired
    public EmployeeListController(
            EmployeeQueries employeeQueries,
            EmployeeAccountQueries employeeAccountQueries,
            EmployeeRoleQueries employeeRoleQueries,
            EmployeeService employeeService, EmployeeFormValidator employeeFormValidator) {

        this.employeeQueries = employeeQueries;
        this.employeeAccountQueries = employeeAccountQueries;
        this.employeeRoleQueries = employeeRoleQueries;
        this.employeeService = employeeService;
        this.employeeFormValidator = employeeFormValidator;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.addValidators(employeeFormValidator);
    }

    @GetMapping("/employeeList")
    String getEmployeeList(Model model) {

        List<EmployeeBasicInfoDto> employeeBasicInfoDtoList = employeeQueries.getEmployeeBasicInfoDtoList();

        model.addAttribute("employeeList", employeeBasicInfoDtoList);

        return "employee-list-view";
    }

    @GetMapping("/editEmployee")
    String getEmployeeForm(@RequestParam(required = false) Long id, Model model) {

        var availableRoles = employeeRoleQueries.getEmployeeRoleDtoList();

        model.addAttribute("availableRoles", availableRoles);

        if (id == null) {

            model.addAttribute("employeeForm", new EmployeeForm());

            return "employee-edit-view";
        }

        model.addAttribute("employeeForm", employeeService.getEmployeeFormFilledFromEntity(id));

        return "employee-edit-view";
    }

    @PostMapping ("/saveEmployee")
    String getEmployeeForm(@ModelAttribute @Valid EmployeeForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "employee-edit-view";
        }

        employeeService.saveEmployeeFromForm(form);

        return "redirect:/employeeList";
    }
}
