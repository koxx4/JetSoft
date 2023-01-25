package org.jetsoft.web.jssystemapp.vehicle.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.vehicle.domain.VehicleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleModelRepository extends JpaRepository<VehicleModel> {
    @Autowired
    VehicleModelRepository(EntityManager entityManager) {

        super(entityManager, VehicleModel.class);
    }
}
