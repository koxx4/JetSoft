package org.jetsoft.web.jssystemapp.flight.application;

import java.util.List;

public interface FlightQueries {

    FlightEmployeeRowDto getFlightEmployeeRowDtoByFlightId(Long id);
    List<FlightEmployeeRowDto> getFlightEmployeeRowDtoListPaginated(int page, int pageSize);
    List<FlightEmployeeRowDto> getFlightEmployeeRowDtoList();
    List<FlightEmployeeRowDto> getFlightEmployeeRowDtoListFilteredByIdList(List<Long> flightIdList);
    List<FlightEmployeeRowDto> getFlightEmployeeRowDtoListWithout(List<Long> flightIdList);
    boolean exists(Long id);
    List<FlightCustomerRowDto> getFlightsForCustomersPaginated(int page, int i);
    FlightCustomerRowDto getFlightCustomerRowDto(Long flightId);
    String getFlightNumberByFlightId(Long id);
}
