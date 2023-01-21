package org.jetsoft.web.jssystemapp.customer.application;

import org.jetsoft.web.jssystemapp.customer.api.CustomerRegistrationForm;
import org.jetsoft.web.jssystemapp.flight.api.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerNewCustomerFromForm(CustomerRegistrationForm customerRegistrationForm) {

        customerRepository.save(new Customer(
                customerRegistrationForm.getFirstName(),
                customerRegistrationForm.getLastName(),
                customerRegistrationForm.getPassword().toString(),
                customerRegistrationForm.getEmail(),
                customerRegistrationForm.getPhone(),
                LocalDateTime.now()
        ));
    }
}
