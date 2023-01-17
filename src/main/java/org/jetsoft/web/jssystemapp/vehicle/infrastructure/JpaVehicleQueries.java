package org.jetsoft.web.jssystemapp.vehicle.infrastructure;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleRowDto;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleQueries;
import org.jetsoft.web.jssystemapp.vehicle.domain.Vehicle;
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
    public List<VehicleRowDto> getVehiclePublicRowDtoListPaginated(int page, int pageSize) {

        return getAllPaginated(page, pageSize).stream()
                .map(vehicle -> {
                    return toVehiclePublicRowDto(vehicle);
                })
                .toList();
    }

    @Override
    public boolean exists(Long id) {

        return super.exists(id);
    }

    private VehicleRowDto toVehiclePublicRowDto(Vehicle vehicle) {

        return new VehicleRowDto(
                vehicle.getId(),
                vehicle.getVehicleTypeId(),
                vehicle.getVehicleModelId(),
                vehicle.getNextMaintenanceDate(),
                vehicle.getFriendlyName(),
                vehicle.getRentDate()
        );
    }
}