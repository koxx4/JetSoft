package org.jetsoft.web.jssystemapp.employee.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.employee.domain.Pilot;
import org.jetsoft.web.jssystemapp.flight.domain.PilotToFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class PilotRepository extends JpaRepository<Pilot> {

    @Autowired
    PilotRepository(EntityManager entityManager) {
        super(entityManager, Pilot.class);
    }

    @Transactional
    public void removePilotFromFlight(Long pilotId, Long flightId) {

        Pilot pilot = get(pilotId);

        PilotToFlight bindingToDelete = pilot.getFlights().stream()
                .filter(binding -> binding.getFlightId().equals(flightId))
                        .findFirst().orElse(null);

        pilot.getFlights().remove(bindingToDelete);

        save(pilot);
    }
}
