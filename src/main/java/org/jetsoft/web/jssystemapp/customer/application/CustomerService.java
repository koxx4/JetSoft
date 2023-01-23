package org.jetsoft.web.jssystemapp.customer.application;

import org.jetsoft.web.jssystemapp.core.utils.StringUtils;
import org.jetsoft.web.jssystemapp.customer.api.CustomerFilterForm;
import org.jetsoft.web.jssystemapp.customer.api.CustomerRegistrationForm;
import org.jetsoft.web.jssystemapp.flight.api.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.jetsoft.web.jssystemapp.core.utils.StringUtils.startsWithBlankPositive;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerQueries customerQueries;

    @Autowired
    CustomerService(CustomerRepository customerRepository, CustomerQueries customerQueries) {

        this.customerRepository = customerRepository;
        this.customerQueries = customerQueries;
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

    public List<CustomerDetailsDto> getCustomerDetailsListFiltered(CustomerFilterForm filter) {

        return customerQueries.getCustomerDetailsList().stream()
                .filter(customer -> startsWithBlankPositive(customer.firstName(), filter.getFirstName()))
                .filter(customer -> startsWithBlankPositive(customer.lastName(), filter.getLastName()))
                .filter(customer -> startsWithBlankPositive(customer.email(), filter.getEmail()))
                .filter(customer -> startsWithBlankPositive(customer.phone(), filter.getPhone()))
                .toList();
    }
}
