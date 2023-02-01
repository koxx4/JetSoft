package org.jetsoft.web.jssystemapp.customer.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.jetsoft.web.jssystemapp.customer.application.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.jetsoft.web.jssystemapp.configuration.security.CommonSecurityConfig.CUSTOMER_ROLE;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public
class CustomerRegistrationController {

    private static final String V_CUSTOMER_REGISTRATION = "customer/customer-registration-view";
    private static final String V_CUSTOMER_REGISTRATION_SUCCESS = "customer/customer-registration-success-view";
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    CustomerRegistrationController(CustomerService customerService, AuthenticationManager authenticationManager) {

        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/registration/customer")
    String showRegistrationForm(Model model) {

        model.addAttribute("registrationForm", new CustomerRegistrationForm());

        return V_CUSTOMER_REGISTRATION;
    }

    @PostMapping ("/registration/customer/create")
    String showRegistrationForm(@ModelAttribute("registrationForm") @Valid CustomerRegistrationForm customerRegistrationForm,
                                BindingResult bindingResult,
                                HttpServletRequest request) {

        if (bindingResult.hasErrors()) {

            return V_CUSTOMER_REGISTRATION;
        }

        customerService.registerNewCustomerFromForm(customerRegistrationForm);

        var token = new UsernamePasswordAuthenticationToken(
                customerRegistrationForm.getEmail(),
                customerRegistrationForm.getPassword(),
                List.of(new SimpleGrantedAuthority(CUSTOMER_ROLE)));

        logInNewCustomer(request, token);

        return V_CUSTOMER_REGISTRATION_SUCCESS;
    }

    private void logInNewCustomer(HttpServletRequest request, UsernamePasswordAuthenticationToken token) {

        Authentication auth = authenticationManager.authenticate(token);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
    }
}
