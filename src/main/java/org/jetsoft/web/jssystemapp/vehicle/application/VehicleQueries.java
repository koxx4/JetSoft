package org.jetsoft.web.jssystemapp.vehicle.application;

import java.util.List;
public interface VehicleQueries {

    // metody do odczytu dto
    List<VehicleRowDto> getVehicleRowDtoListPaginated(int page, int pageSize);
    List<VehicleInFlightFormDto> getVehicleInFlightFormDtoList();
    List<VehicleModelInVehicleFormDto> getVehicleModelInVehicleFormDtoList();
    String getModelNameByModelId(Long id);
    String getTypeNameByTypeId(Long id);
    int getMaxPassengerCount(Long id);
    int getMaxDistance(Long id);
    int getMaxPilotCount(Long id);
    int getLiftCapacity(Long id);
    boolean exists(Long id);
}
