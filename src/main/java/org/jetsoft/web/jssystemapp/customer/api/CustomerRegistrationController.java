package org.jetsoft.web.jssystemapp.customer.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.customer.application.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class CustomerRegistrationController {

    private final CustomerService customerService;

    @Autowired
    CustomerRegistrationController(CustomerService customerService) {

        this.customerService = customerService;
    }


    @GetMapping("/customer/registration")
    String showRegistrationForm(Model model) {

        model.addAttribute("registrationForm", new CustomerRegistrationForm());

        return "customer-registration-view";
    }

    @PostMapping ("/customer/registration/create")
    String showRegistrationForm(@ModelAttribute("registrationForm") @Valid CustomerRegistrationForm customerRegistrationForm,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "customer-registration-view";
        }

        customerService.registerNewCustomerFromForm(customerRegistrationForm);

        return "customer-registration-success-view";
    }
}
