package org.jetsoft.web.jssystemapp.flight.application;

import java.util.List;

public interface FlightQueries {

    FlightEmployeeRowDto getFlightPublicRowDtoByFlightId(Long id);
    List<FlightEmployeeRowDto> getFlightPublicRowDtoListPaginated(int page, int pageSize);
    boolean exists(Long id);
}
