package org.jetsoft.web.jssystemapp.flight.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jetsoft.web.jssystemapp.core.JpaQueries;
import org.jetsoft.web.jssystemapp.flight.application.FlightRouteQueries;
import org.jetsoft.web.jssystemapp.flight.domain.Flight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class JpaFlightRouteQueries extends JpaQueries<Flight> implements FlightRouteQueries {

    protected JpaFlightRouteQueries(EntityManager entityManager) {
        super(entityManager, Flight.class);
    }

    @Override
    @Transactional
    public int getFlightsCountAssignedToRoute(Long routeId) {

        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<Flight> criteriaQuery = getCriteriaQuery(criteriaBuilder);
        Root<Flight> root = criteriaQuery.from(Flight.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("routeId"), routeId));

        return getEntityManager().createQuery(criteriaQuery)
                .getResultList().size();
    }

    @Override
    public boolean hasRouteAssignedAnyFlights(Long routeId) {
        return getFlightsCountAssignedToRoute(routeId) > 0;
    }
}
