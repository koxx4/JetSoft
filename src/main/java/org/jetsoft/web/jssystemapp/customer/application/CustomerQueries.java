package org.jetsoft.web.jssystemapp.customer.application;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface CustomerQueries {

    Optional<UserDetails> findCustomerAccountInfoByEmail(String email);
    Long getCustomerIdByCustomerEmail(String email);
    CustomerNameAndEmailDto getCustomerNameAndEmailDto(Long customerId);
}
