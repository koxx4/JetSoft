package org.jetsoft.web.jssystemapp.customer.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.flight.api.domain.Customer;
import org.springframework.stereotype.Service;

@Service
class CustomerRepository extends JpaRepository<Customer> {

    protected CustomerRepository(EntityManager entityManager) {
        super(entityManager, Customer.class);
    }
}
