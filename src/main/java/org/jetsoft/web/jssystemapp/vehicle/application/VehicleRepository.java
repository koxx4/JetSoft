package org.jetsoft.web.jssystemapp.vehicle.application;

import org.jetsoft.web.jssystemapp.vehicle.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository {

    // odczyt i zapis encji bezposrednio + działanie na bazie
    void save(Vehicle vehicle);
    Vehicle get(Long id);
    void remove(Long id);
}
