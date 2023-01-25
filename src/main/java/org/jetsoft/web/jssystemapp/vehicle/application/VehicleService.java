package org.jetsoft.web.jssystemapp.vehicle.application;

import org.jetsoft.web.jssystemapp.vehicle.api.VehicleForm;
import org.jetsoft.web.jssystemapp.vehicle.domain.Vehicle;
import org.jetsoft.web.jssystemapp.vehicle.domain.VehicleModel;
import org.jetsoft.web.jssystemapp.vehicle.domain.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.util.Strings.isEmpty;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleQueries vehicleQueries;
    private final VehicleModelQueries vehicleModelQueries;
    private final VehicleTypeQueries vehicleTypeQueries;
    private final VehicleModelRepository vehicleModelRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository,
                          VehicleQueries vehicleQueries,
                          VehicleModelQueries vehicleModelQueries,
                          VehicleTypeQueries vehicleTypeQueries,
                          VehicleModelRepository vehicleModelRepository,
                          VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleQueries = vehicleQueries;
        this.vehicleModelQueries = vehicleModelQueries;
        this.vehicleTypeQueries = vehicleTypeQueries;
        this.vehicleModelRepository = vehicleModelRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    public void removeVehicleById(Long id) {

        vehicleRepository.remove(id);
    }

    public VehicleForm getFilledFormFromVehicle(Long id) {

        var form = new VehicleForm();

        var vehicle = vehicleRepository.get(id);

        var vehicleModel = vehicleModelQueries.getVehicleModelDtoById(id);

        form.setId(vehicle.getId());
        form.setVehicleTypeId(vehicle.getVehicleTypeId());
        form.setVehicleModelId(vehicle.getVehicleModelId());
        form.setFriendlyName(vehicle.getFriendlyName());
        form.setRentDate(vehicle.getRentDate());
        form.setNextMaintenanceDate(vehicle.getNextMaintenanceDate());
        form.setMaxPassengersCount(vehicleModel.maxPassengerCount());
        form.setMaxPilotCount(vehicleModel.maxPilotCount());
        form.setMaxDistance(vehicleModel.maxDistance());
        form.setMaxLift(vehicleModel.liftCapacity());

        return form;
    }

    public void saveVehicleFromForm(VehicleForm vehicleForm) {

        if (vehicleQueries.exists(vehicleForm.getId())) {

            Vehicle vehicle = vehicleRepository.get(vehicleForm.getId());

            updateVehicle(vehicle, vehicleForm);

            vehicleRepository.save(vehicle);

            return;
        }

        vehicleRepository.save(toVehicle(vehicleForm));
    }

    private void updateVehicle(Vehicle vehicle, VehicleForm vehicleForm) {

        vehicle.setFriendlyName(vehicleForm.getFriendlyName());
        vehicle.setVehicleTypeId(vehicleForm.getVehicleTypeId());
        vehicle.setVehicleModelId(vehicleForm.getVehicleModelId());
        vehicle.setRentDate(vehicleForm.getRentDate());
        vehicle.setNextMaintenanceDate(vehicleForm.getNextMaintenanceDate());

        VehicleModel vehicleModel = vehicleModelRepository.get(vehicle.getVehicleModelId());

        if (!isEmpty(vehicleForm.getModelName())) {

            vehicleModel.setModelName(vehicleForm.getModelName());
        }
        vehicleModel.setLiftCapacity(vehicleForm.getMaxLift());
        vehicleModel.setMaxPilotCount(vehicleForm.getMaxPilotCount());
        vehicleModel.setMaxDistance(vehicleForm.getMaxDistance());
        vehicleModel.setMaxPassengerCount(vehicleForm.getMaxPassengersCount());

        VehicleType vehicleType = vehicleTypeRepository.get(vehicle.getVehicleTypeId());

        if (!isEmpty(vehicleForm.getTypeName())) {

            vehicleType.setTypeName(vehicleForm.getTypeName());
        }

        vehicleTypeRepository.save(vehicleType);
        vehicleModelRepository.save(vehicleModel);
    }

    private Vehicle toVehicle(VehicleForm vehicleForm) {

        return new Vehicle(
                vehicleForm.getId(),
                vehicleForm.getVehicleTypeId(),
                vehicleForm.getVehicleModelId(),
                vehicleForm.getNextMaintenanceDate(),
                vehicleForm.getFriendlyName(),
                vehicleForm.getRentDate()
        );
    }
}
