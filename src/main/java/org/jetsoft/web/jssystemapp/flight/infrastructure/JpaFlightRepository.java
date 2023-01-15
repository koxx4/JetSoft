package org.jetsoft.web.jssystemapp.flight.infrastructure;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.flight.application.FlightRepository;
import org.jetsoft.web.jssystemapp.flight.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaFlightRepository extends JpaRepository<Flight> implements FlightRepository {

    @Autowired
    public JpaFlightRepository(EntityManager entityManager) {

        super(entityManager, Flight.class);
    }
}
