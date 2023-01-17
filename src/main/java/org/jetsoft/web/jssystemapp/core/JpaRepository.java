package org.jetsoft.web.jssystemapp.core;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
    public void remove(Long id) {

         T entity = get(id);

         if (entity == null) {

             throw new EntityNotFoundException(
                     String.format("Entity of %s id(%d) not found!", entityClass.getSimpleName(), id));
         }

        entityManager.remove(entity);
    }

    @Transactional
    public T get(Long id) {

        return entityManager.find(entityClass, id);
    }
}
