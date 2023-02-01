package org.jetsoft.web.jssystemapp.customer.application;

import org.jetsoft.web.jssystemapp.customer.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles(profiles = "dev")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldSaveCustomer() {

        //given
        Customer customer = new Customer(
                "John",
                "Doe",
                "password",
                "email@com.pl",
                "+48123456789",
                LocalDateTime.now()
        );

        //when
        customerRepository.save(customer);

        //then
        Customer saved = customerRepository.get(customer.getId());

        assertThat(saved.getEmail()).isEqualTo(customer.getEmail());
        assertThat(saved.getPassword()).isEqualTo(customer.getPassword());
        assertThat(saved.getPhone()).isEqualTo(customer.getPhone());
        assertThat(saved.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(saved.getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    void shouldFindCustomerByEmail() {

        //given
        Customer customer = new Customer(
                "John",
                "Doe",
                "password",
                "john.doe@pwr.edu.pl",
                "+48123456789",
                LocalDateTime.now()
        );

        //when
        customerRepository.save(customer);

        //then
        Customer saved = customerRepository.getByEmail("john.doe@pwr.edu.pl");

        assertThat(saved.getEmail()).isEqualTo(customer.getEmail());
        assertThat(saved.getPassword()).isEqualTo(customer.getPassword());
        assertThat(saved.getPhone()).isEqualTo(customer.getPhone());
        assertThat(saved.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(saved.getLastName()).isEqualTo(customer.getLastName());
    }
}