package org.jetsoft.web.jssystemapp.location.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.location.domain.Route;
import org.springframework.stereotype.Repository;

@Repository
class RouteRepository extends JpaRepository<Route> {

    protected RouteRepository(EntityManager entityManager) {
        super(entityManager, Route.class);
    }
}
