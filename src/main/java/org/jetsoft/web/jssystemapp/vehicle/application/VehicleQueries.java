package org.jetsoft.web.jssystemapp.vehicle.application;

import java.util.List;
public interface VehicleQueries {

    // metody do odczytu dto
    List<VehicleRowDto> getVehiclePublicRowDtoListPaginated(int page, int pageSize);
    boolean exists(Long id);
}
