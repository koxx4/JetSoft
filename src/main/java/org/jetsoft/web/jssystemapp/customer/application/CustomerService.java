package org.jetsoft.web.jssystemapp.customer.application;

import org.jetsoft.web.jssystemapp.customer.api.CustomerFilterForm;
import org.jetsoft.web.jssystemapp.customer.api.CustomerProfileForm;
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

    public void updateUserProfileFromProfileForm(CustomerProfileForm customerProfileForm, String customerEmail) {

        Customer customer = customerRepository.getByEmail(customerEmail);

        customer.setEmail(customerProfileForm.getEmail());
        customer.setFirstName(customerProfileForm.getFirstName());
        customer.setLastName(customerProfileForm.getLastName());
        customer.setPhone(customerProfileForm.getPhone());

        customerRepository.save(customer);
    }

    public CustomerProfileForm getFilledCustomerProfileFormByEmail(String email) {

        Customer customer = customerRepository.getByEmail(email);

        CustomerProfileForm customerProfileForm = new CustomerProfileForm();
        customerProfileForm.setEmail(customer.getEmail());
        customerProfileForm.setFirstName(customer.getFirstName());
        customerProfileForm.setLastName(customer.getLastName());
        customerProfileForm.setPhone(customer.getPhone());

        return customerProfileForm;
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
