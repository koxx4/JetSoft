package org.jetsoft.web.jssystemapp.flight.application;

import java.util.List;

public interface FlightQueries {

    FlightPublicRowDto getFlightPublicRowDtoByFlightId(Long id);
    List<FlightPublicRowDto> getFlightPublicRowDtoListPaginated(int page, int pageSize);
    boolean exists(Long id);
}
