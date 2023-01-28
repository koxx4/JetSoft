package org.jetsoft.web.jssystemapp.customer.api;

import org.jetsoft.web.jssystemapp.customer.application.CustomerDetailsDto;
import org.jetsoft.web.jssystemapp.customer.application.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
class CustomerListController {

    private final CustomerService customerService;

    @Autowired
    CustomerListController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ModelAttribute("customersFilterForm")
    private CustomerFilterForm addCustomerFilterFormToModel() {

        return new CustomerFilterForm();
    }

    @GetMapping("/employee/customerList")
    String listAllCustomersForEmployees(@ModelAttribute CustomerFilterForm customersFilterForm, Model model) {

        List<CustomerDetailsDto> customers = customerService.getCustomerDetailsListFiltered(customersFilterForm);

        model.addAttribute("customers", customers);

        return "customers-for-employees-list-view";
    }
}
