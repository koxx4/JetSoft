package org.jetsoft.web.jssystemapp.vehicle.infrastructure;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleModelDto;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleModelQueries;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleTypeDto;
import org.jetsoft.web.jssystemapp.vehicle.domain.VehicleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class JpaVehicleModelQueries extends JpaQueries<VehicleModel> implements VehicleModelQueries {

    @Autowired
    JpaVehicleModelQueries(EntityManager entityManager) {

        super(entityManager, VehicleModel.class);
    }
    @Override
    public List<VehicleModelDto> getVehicleModelDtoList() {

        List<VehicleModel> vehicleModels = getAll();

        return vehicleModels.stream().map(vehicleModel -> new VehicleModelDto(
                vehicleModel.getId(),
                vehicleModel.getModelName(),
                vehicleModel.getMaxPassengerCount(),
                vehicleModel.getMaxDistance(),
                vehicleModel.getMaxPilotCount(),
                vehicleModel.getLiftCapacity())).toList();
    }

    @Override
    public VehicleModelDto getVehicleModelDtoById(Long id) {

        var vehicleModel = getById(id);

        return new VehicleModelDto(
                vehicleModel.getId(),
                vehicleModel.getModelName(),
                vehicleModel.getMaxPassengerCount(),
                vehicleModel.getMaxDistance(),
                vehicleModel.getMaxPilotCount(),
                vehicleModel.getLiftCapacity()
        );
    }
}
