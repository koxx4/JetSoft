package org.jetsoft.web.jssystemapp.customer.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.flight.api.domain.Customer;
import org.springframework.stereotype.Service;

@Service
class CustomerRepository extends JpaRepository<Customer> {

    CustomerRepository(EntityManager entityManager) {
        super(entityManager, Customer.class);
    }

    Customer getByEmail(String email) {

        var criteriaBuilder = getCriteriaBuilder();
        var criteriaQuery = getCriteriaQuery(criteriaBuilder);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get("email"), email));

        return getEntityManager()
                .createQuery(criteriaQuery)
                .getSingleResult();
    }
}
