package org.jetsoft.web.jssystemapp.customer.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.customer.application.CustomerQueries;
import org.jetsoft.web.jssystemapp.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.jetsoft.web.jssystemapp.configuration.security.CommonSecurityConfig.CUSTOMER_ROLE;

@Service
class JpaCustomerQueries extends JpaQueries<Customer> implements CustomerQueries {

    @Autowired
    JpaCustomerQueries(EntityManager entityManager) {
        super(entityManager, Customer.class);
    }

    @Override
    public Optional<UserDetails> getCustomerAccountInfoByEmail(String email) {

        var criteriaBuilder = getCriteriaBuilder();
        var criteriaQuery = getCriteriaQuery(criteriaBuilder);
        Root<Customer> root = criteriaQuery.from(Customer.class);

        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get("email"), email));

        try {

            Customer customer = getEntityManager()
                    .createQuery(criteriaQuery)
                    .getSingleResult();

            List<SimpleGrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(CUSTOMER_ROLE));

            return Optional.of(new User(customer.getEmail(), customer.getPassword(), authorityList));
        }
        catch (Exception e) {

            return Optional.empty();
        }
    }
}
