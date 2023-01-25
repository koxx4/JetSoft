package org.jetsoft.web.jssystemapp.vehicle.infrastructure;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.vehicle.application.VehicleRepository;
import org.jetsoft.web.jssystemapp.vehicle.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class JpaVehicleRepository extends JpaRepository<Vehicle> implements VehicleRepository {

    @Autowired
    JpaVehicleRepository(EntityManager entityManager) {

        super(entityManager, Vehicle.class);
    }
}
