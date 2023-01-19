package org.jetsoft.web.jssystemapp.flight.application;

import java.util.List;

public interface FlightQueries {

    FlightEmployeeRowDto getFlightEmployeeRowDtoByFlightId(Long id);
    List<FlightEmployeeRowDto> getFlightEmployeeRowDtoListPaginated(int page, int pageSize);
    boolean exists(Long id);
    List<FlightCustomerRowDto> getFlightsForCustomersPaginated(int page, int i);
}
