package org.jetsoft.web.jssystemapp.vehicle.application;

import java.util.List;

public interface VehicleModelQueries {

    List<VehicleModelDto> getVehicleModelDtoList();
    VehicleModelDto getVehicleModelDtoById(Long id);
}
