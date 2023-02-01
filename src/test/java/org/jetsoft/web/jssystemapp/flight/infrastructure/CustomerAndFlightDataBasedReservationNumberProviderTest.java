package org.jetsoft.web.jssystemapp.flight.infrastructure;

import org.jetsoft.web.jssystemapp.customer.application.CustomerNameAndEmailDto;
import org.jetsoft.web.jssystemapp.customer.application.CustomerQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightCustomerRowDto;
import org.jetsoft.web.jssystemapp.flight.application.FlightQueries;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerAndFlightDataBasedReservationNumberProviderTest {

    @InjectMocks
    private CustomerAndFlightDataBasedReservationNumberProvider provider;

    @Mock
    private CustomerQueries customerQueries;
    @Mock
    private FlightQueries flightQueries;


    @Test
    void shouldGenerateProperReservationNumber() {

        String flightNumber = "FLIGHTNUMBER";
        FlightCustomerRowDto flightCustomerRowDto = new FlightCustomerRowDto(
                    null,
                    flightNumber,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
        );

        //given
        given(customerQueries.getCustomerNameAndEmailDto(anyLong()))
                .willReturn(new CustomerNameAndEmailDto("John", "Doe", "john.doe@gmail.com"));

        given(flightQueries.getFlightCustomerRowDto(anyLong()))
                .willReturn(flightCustomerRowDto);

        //when
        String generated = provider.generateReservationNumber(1L, 1L);

        //then
        String expected = "JOHJOHDOE-" + flightNumber + "-0";

        assertThat(generated).isEqualTo(expected);
    }

}