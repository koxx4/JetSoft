package org.jetsoft.web.jssystemapp.vehicle.application;

public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleQueries vehicleQueries;

    public VehicleService(VehicleRepository vehicleRepository, VehicleQueries vehicleQueries) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleQueries = vehicleQueries;
    }
}
