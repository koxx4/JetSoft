package org.jetsoft.web.jssystemapp.location.application;

import jakarta.persistence.EntityManager;
import org.jetsoft.web.jssystemapp.core.JpaRepository;
import org.jetsoft.web.jssystemapp.location.domain.City;
import org.springframework.stereotype.Repository;

@Repository
class CityRepository extends JpaRepository<City> {

    protected CityRepository(EntityManager entityManager) {
        super(entityManager, City.class);
    }
}
