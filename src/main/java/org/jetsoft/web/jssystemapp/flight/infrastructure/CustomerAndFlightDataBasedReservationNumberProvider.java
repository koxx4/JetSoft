package org.jetsoft.web.jssystemapp.flight.infrastructure;

import org.jetsoft.web.jssystemapp.customer.application.CustomerNameAndEmailDto;
import org.jetsoft.web.jssystemapp.customer.application.CustomerQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightCustomerRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.jetsoft.web.jssystemapp.flight.application.ReservationNumberProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CustomerAndFlightDataBasedReservationNumberProvider implements ReservationNumberProvider {

    private final static char HYPHEN = '-';
    private Long globalCounter = 0L;

    private final CustomerQueries customerQueries;
    private final FlightQueries flightQueries;

    @Autowired
    CustomerAndFlightDataBasedReservationNumberProvider(CustomerQueries customerQueries, FlightQueries flightQueries) {
        this.customerQueries = customerQueries;
        this.flightQueries = flightQueries;
    }

    @Override
    public String generateReservationNumber(Long flightId, Long customerId) {

        CustomerNameAndEmailDto customerNameAndEmail = customerQueries.getCustomerNameAndEmailDto(customerId);
        FlightCustomerRowDto flightData = flightQueries.getFlightCustomerRowDto(flightId);

        String reservationNumber = new StringBuilder()
                .append(customerNameAndEmail.firstName().substring(0, 3).toUpperCase())
                .append(customerNameAndEmail.email().substring(0, 3).toUpperCase())
                .append(customerNameAndEmail.lastName().substring(0, 3).toUpperCase())
                .append(HYPHEN)
                .append(flightData.flightNumber())
                .append(HYPHEN)
                .append(globalCounter)
                .toString();

        return reservationNumber;
    }
}
