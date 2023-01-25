package org.jetsoft.web.jssystemapp.flight.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.flight.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ReservationRepository extends JpaRepository<Reservation> {

    @Autowired
    ReservationRepository(EntityManager entityManager) {
        super(entityManager, Reservation.class);
    }
}
