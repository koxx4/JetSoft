package org.jetsoft.web.jssystemapp.vehicle.infrastructure;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleTypeDto;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleTypeQueries;
import org.jetsoft.web.jssystemapp.vehicle.domain.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class JpaVehicleTypeQueries extends JpaQueries<VehicleType> implements VehicleTypeQueries {
    @Autowired
    JpaVehicleTypeQueries(EntityManager entityManager) {
        super(entityManager, VehicleType.class);
    }

    @Override
    public List<VehicleTypeDto> getVehicleTypeDtoList() {

        List<VehicleType> vehicleTypes = getAll();

        return vehicleTypes.stream().map(vehicleType -> new VehicleTypeDto(vehicleType.getId(), vehicleType.getTypeName()))
                .toList();
    }
}
