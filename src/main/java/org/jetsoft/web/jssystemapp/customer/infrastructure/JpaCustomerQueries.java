package org.jetsoft.web.jssystemapp.customer.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Root;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.customer.application.CustomerNameAndEmailDto;
import org.jetsoft.web.jssystemapp.customer.application.CustomerQueries;
import org.jetsoft.web.jssystemapp.flight.api.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Optional<UserDetails> findCustomerAccountInfoByEmail(String email) {

        Optional<Customer> optionalCustomer = findCustomerByEmail(email);

        if (optionalCustomer.isEmpty())
            return Optional.empty();

        Customer customer = optionalCustomer.get();
        List<SimpleGrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(CUSTOMER_ROLE));

        return Optional.of(new User(customer.getEmail(), customer.getPassword(), authorityList));
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCustomerIdByCustomerEmail(String email) {

        return findCustomerByEmail(email)
                .map(Customer::getId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public CustomerNameAndEmailDto getCustomerNameAndEmailDto(Long customerId) {

        return findById(customerId)
                .map(this::toCustomerNameAndEmailDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    private CustomerNameAndEmailDto toCustomerNameAndEmailDto(Customer customer) {

        return new CustomerNameAndEmailDto(customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }

    private Optional<Customer> findCustomerByEmail(String email) {

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

            return Optional.of(customer);
        }
        catch (Exception e) {

            return Optional.empty();
        }
    }
}
