package org.jetsoft.web.jssystemapp.customer.api;

import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.customer.application.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
class CustomerProfileEditController {

    private static final String V_CUSTOMER_CUSTOMER_PROFILE_EDIT_RESULT = "customer/customer-profile-edit-result-view";
    private final CustomerService customerService;

    @Autowired
    CustomerProfileEditController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer/saveProfile")
    String saveCustomerFromForm(@ModelAttribute @Valid CustomerProfileForm customerProfileForm, Model model, Principal principal) {

        boolean errorSavingProfileData = false;

        try {

            customerService.updateUserProfileFromProfileForm(customerProfileForm, principal.getName());
        } catch (Exception exception) {

            errorSavingProfileData = true;
        }

        model.addAttribute("errorSavingProfileData", errorSavingProfileData);

        return V_CUSTOMER_CUSTOMER_PROFILE_EDIT_RESULT;
    }
}
