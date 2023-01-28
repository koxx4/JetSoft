package org.jetsoft.web.jssystemapp.customer.application;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface CustomerQueries {

    Optional<UserDetails> findCustomerAccountInfoByEmail(String email);
    List<CustomerDetailsDto> getCustomerDetailsList();
    CustomerDetailsDto getCustomerDetailsByEmail(String email);
    CustomerProfileDto getCustomerProfileDtoByEmail(String email);
    Long getCustomerIdByCustomerEmail(String email);
    CustomerNameAndEmailDto getCustomerNameAndEmailDto(Long customerId);
    boolean hasReservationOnThisFlight(Long customerId, String flightNumber);
}
