package org.jetsoft.web.jssystemapp.core;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public abstract class JpaQueries<T extends AbstractEntity> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    protected JpaQueries(EntityManager entityManager, Class<T> entityClass) {

        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    protected CriteriaBuilder getCriteriaBuilder() {

        return entityManager.getCriteriaBuilder();
    }

    protected CriteriaQuery<T> getCriteriaQuery(CriteriaBuilder criteriaBuilder) {

        return criteriaBuilder.createQuery(entityClass);
    }

    protected List<T> getAll() {

        CriteriaQuery<T> criteriaQuery = getCriteriaQuery(getCriteriaBuilder());
        Root<T> root = criteriaQuery.from(entityClass);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    protected List<T> getAllPaginated(int page, int pageSize) {

        CriteriaQuery<T> criteriaQuery = getCriteriaQuery(getCriteriaBuilder());
        Root<T> root = criteriaQuery.from(entityClass);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(page * pageSize)
                .setMaxResults(page * pageSize + pageSize)
                .getResultList();
    }

    protected Optional<T> findById(Long id) {

        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    protected <E extends AbstractEntity> Optional<E> findById(Class<E> clazz, Long id) {

        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    protected T getById(Long id) {

        return entityManager.find(entityClass, id);
    }

    protected <E extends AbstractEntity> E getById(Class<E> clazz, Long id) {

        return entityManager.find(clazz, id);
    }

    protected void saveOrUpdate(T entity) {

        entityManager.persist(entity);
    }

    protected EntityManager getEntityManager() {

        return entityManager;
    }
}
