package org.jetsoft.web.jssystemapp.customer.application;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface CustomerQueries {

    Optional<UserDetails> getCustomerAccountInfoByEmail(String email);
}
