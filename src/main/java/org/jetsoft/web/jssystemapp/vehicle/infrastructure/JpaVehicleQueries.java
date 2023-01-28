package org.jetsoft.web.jssystemapp.vehicle.infrastructure;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.*;
import org.jetsoft.web.jssystemapp.vehicle.domain.Vehicle;
import org.jetsoft.web.jssystemapp.vehicle.domain.VehicleModel;
import org.jetsoft.web.jssystemapp.vehicle.domain.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JpaVehicleQueries extends JpaQueries<Vehicle> implements VehicleQueries {

    @Autowired
    JpaVehicleQueries(EntityManager entityManager) {

        super(entityManager, Vehicle.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleRowDto> getVehicleRowDtoListPaginated(int page, int pageSize) {

        return getAllPaginated(page, pageSize).stream()
                .map(this::toVehiclePublicRowDto)
                .toList();
    }

    @Override
    public List<VehicleInFlightFormDto> getVehicleInFlightFormDtoList() {

        return getAll().stream()
                .map(this::toVehicleInFlightFormDto)
                .toList();
    }

    @Override
    public boolean exists(Long id) {

        return super.exists(id);
    }

    @Override
    public String getModelNameByModelId(Long id) {

        return getById(VehicleModel.class, id)
                .getModelName();
    }

    @Override
    public String getTypeNameByTypeId(Long id) {

        return getById(VehicleType.class, id)
                .getTypeName();
    }

    @Override
    public int getMaxPassengerCount(Long id) {
        return getById(VehicleModel.class, id)
                .getMaxPassengerCount();
    }

    @Override
    public int getMaxDistance(Long id) {
        return getById(VehicleModel.class, id)
                .getMaxDistance();
    }

    @Override
    public int getMaxPilotCount(Long id) {
        return getById(VehicleModel.class, id)
                .getMaxPilotCount();
    }

    @Override
    public int getLiftCapacity(Long id) {
        return getById(VehicleModel.class, id)
                .getLiftCapacity();
    }

    private VehicleRowDto toVehiclePublicRowDto(Vehicle vehicle) {

        String vehicleTypeName = getTypeNameByTypeId(vehicle.getVehicleTypeId());
        String vehicleModelName = getModelNameByModelId(vehicle.getVehicleModelId());
        int vehicleMaxPassengerCount = getMaxPassengerCount(vehicle.getVehicleModelId());
        int vehicleMaxDistance = getMaxDistance(vehicle.getVehicleModelId());
        int vehicleMaxPilotCount = getMaxPilotCount(vehicle.getVehicleModelId());
        int vehicleLiftCapacity = getLiftCapacity(vehicle.getVehicleModelId());

        return new VehicleRowDto(
                vehicle.getId(),
                vehicleTypeName,
                vehicleModelName,
                vehicle.getNextMaintenanceDate(),
                vehicle.getFriendlyName(),
                vehicle.getRentDate(),
                vehicleMaxPassengerCount,
                vehicleMaxDistance,
                vehicleMaxPilotCount,
                vehicleLiftCapacity
        );
    }

    private VehicleInFlightFormDto toVehicleInFlightFormDto(Vehicle vehicle) {

        String vehicleTypeName = getTypeNameByTypeId(vehicle.getVehicleTypeId());
        String vehicleModelName = getModelNameByModelId(vehicle.getVehicleModelId());
        int vehicleMaxPassengerCount = getMaxPassengerCount(vehicle.getVehicleModelId());
        int vehicleMaxDistance = getMaxDistance(vehicle.getVehicleModelId());
        int vehicleMaxPilotCount = getMaxPilotCount(vehicle.getVehicleModelId());

        return new VehicleInFlightFormDto(
                vehicle.getId(),
                vehicleTypeName,
                vehicleModelName,
                vehicle.getFriendlyName(),
                vehicleMaxPassengerCount,
                vehicleMaxPilotCount,
                vehicleMaxDistance
        );
    }
}
