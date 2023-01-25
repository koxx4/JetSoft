package org.jetsoft.web.jssystemapp.vehicle.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.vehicle.domain.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleTypeRepository extends JpaRepository<VehicleType> {

    @Autowired
    VehicleTypeRepository(EntityManager entityManager) {

        super(entityManager, VehicleType.class);
    }
}
