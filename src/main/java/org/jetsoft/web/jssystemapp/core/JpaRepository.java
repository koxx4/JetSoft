package org.jetsoft.web.jssystemapp.core;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

public abstract class JpaRepository<T extends AbstractEntity> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    protected JpaRepository(EntityManager entityManager, Class<T> entityClass) {

        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Transactional
    public void save(T entity) {

        entityManager.persist(entity);
    }

    @Transactional
    public T get(Long id) {

        return entityManager.find(entityClass, id);
    }
}
