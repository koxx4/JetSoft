package org.jetsoft.web.jssystemapp.vehicle.application;

import java.util.List;
public interface VehicleQueries {

    // metody do odczytu dto
    List<VehicleRowDto> getVehicleRowDtoListPaginated(int page, int pageSize);
    List<VehicleInFlightFormDto> getVehicleInFlightFormDtoList();
    boolean exists(Long id);
}
