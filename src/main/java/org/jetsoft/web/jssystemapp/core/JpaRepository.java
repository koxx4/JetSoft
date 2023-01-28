package org.jetsoft.web.jssystemapp.core;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public abstract class JpaRepository<T extends JpaEntity> {

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

    @Transactional
    public Optional<T> find(Long id) {

        if (id == null) {

            return Optional.empty();
        }

        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    protected EntityManager getEntityManager() {

        return entityManager;
    }

    protected CriteriaBuilder getCriteriaBuilder() {

        return entityManager.getCriteriaBuilder();
    }

    protected CriteriaQuery<T> getCriteriaQuery(CriteriaBuilder criteriaBuilder) {

        return criteriaBuilder.createQuery(entityClass);
    }
}
