package org.jetsoft.web.jssystemapp.vehicle.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
