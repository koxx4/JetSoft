package org.jetsoft.web.jssystemapp.customer.api;

import org.jetsoft.web.jssystemapp.customer.application.CustomerProfileDto;
import org.jetsoft.web.jssystemapp.customer.application.CustomerQueries;
import org.jetsoft.web.jssystemapp.customer.application.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
class CustomerProfileController {

    private final CustomerQueries customerQueries;
    private final CustomerService customerService;

    @Autowired
    CustomerProfileController(CustomerQueries customerQueries, CustomerService customerService) {
        this.customerQueries = customerQueries;
        this.customerService = customerService;
    }


    @GetMapping("/customer/profileView")
    String getProfileView(Model model, Principal principal) {

        String customerEmail = principal.getName();

        CustomerProfileDto customerProfile = customerQueries.getCustomerProfileDtoByEmail(customerEmail);
        CustomerProfileForm profileForm = customerService.getFilledCustomerProfileFormByEmail(customerEmail);

        model.addAttribute("profile", customerProfile);
        model.addAttribute("profileForm", profileForm);

        return "customer-profile-view";
    }
}
