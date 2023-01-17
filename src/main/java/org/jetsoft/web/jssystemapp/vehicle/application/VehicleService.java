package org.jetsoft.web.jssystemapp.vehicle.application;

import org.jetsoft.web.jssystemapp.vehicle.api.VehicleForm;
import org.jetsoft.web.jssystemapp.vehicle.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleQueries vehicleQueries;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository,
                          VehicleQueries vehicleQueries) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleQueries = vehicleQueries;
    }

    public void removeVehicleById(Long id) {

        vehicleRepository.remove(id);
    }

    public VehicleForm getFilledFormFromVehicle(Long id) {

        var form = new VehicleForm();

        var vehicle = vehicleRepository.get(id);

        form.setId(vehicle.getId());
        form.setVehicleTypeId(vehicle.getVehicleTypeId());
        form.setVehicleModelId(vehicle.getVehicleModelId());
        form.setFriendlyName(vehicle.getFriendlyName());
        form.setRentDate(vehicle.getRentDate());
        form.setNextMaintenanceDate(vehicle.getNextMaintenanceDate());

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
    }

    private Vehicle toVehicle(VehicleForm vehicleForm) {

        return new Vehicle(
                vehicleForm.getVehicleTypeId(),
                vehicleForm.getVehicleModelId(),
                vehicleForm.getNextMaintenanceDate(),
                vehicleForm.getFriendlyName(),
                vehicleForm.getRentDate()
        );
    }
}
