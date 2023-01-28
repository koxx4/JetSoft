package org.jetsoft.web.jssystemapp.employee.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeRoleDto;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeRoleQueries;
import org.jetsoft.web.jssystemapp.employee.application.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
class EmployeeEditController {

    private final EmployeeRoleQueries employeeRoleQueries;
    private final EmployeeService employeeService;
    private final EmployeeFormValidator employeeFormValidator;

    @Autowired
    EmployeeEditController(
            EmployeeRoleQueries employeeRoleQueries,
            EmployeeService employeeService,
            EmployeeFormValidator employeeFormValidator) {

        this.employeeRoleQueries = employeeRoleQueries;
        this.employeeService = employeeService;
        this.employeeFormValidator = employeeFormValidator;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.addValidators(employeeFormValidator);
    }

    @ModelAttribute("availableRoles")
    private List<EmployeeRoleDto> addAvailableRolesToModel() {

       return employeeRoleQueries.getEmployeeRoleDtoList();
    }

    @GetMapping("/employee/editEmployee")
    String getEmployeeForm(@RequestParam(required = false) Long id, Model model) {

        if (id == null) {

            model.addAttribute("employeeForm", new EmployeeForm());

            return "employee-edit-view";
        }

        model.addAttribute("employeeForm", employeeService.getEmployeeFormFilledFromEntity(id));

        return "employee-edit-view";
    }

    @PostMapping("/employee/saveEmployee")
    String getEmployeeForm(@ModelAttribute @Valid EmployeeForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "employee-edit-view";
        }

        employeeService.saveEmployeeFromForm(form);

        return "redirect:/employee/employeeList";
    }
}
