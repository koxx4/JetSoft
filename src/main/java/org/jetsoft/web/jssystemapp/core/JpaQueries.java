package org.jetsoft.web.jssystemapp.core;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class JpaQueries<T extends JpaEntity> {

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

    @Transactional
    public List<T> getAll() {

        CriteriaQuery<T> criteriaQuery = getCriteriaQuery(getCriteriaBuilder());
        Root<T> root = criteriaQuery.from(entityClass);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public List<T> getAllPaginated(int page, int pageSize) {

        CriteriaQuery<T> criteriaQuery = getCriteriaQuery(getCriteriaBuilder());
        Root<T> root = criteriaQuery.from(entityClass);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(page * pageSize)
                .setMaxResults(page * pageSize + pageSize)
                .getResultList();
    }

    @Transactional
    public Optional<T> findById(Long id) {

        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    protected <E extends AbstractEntityWithGeneratedId> Optional<E> findById(Class<E> clazz, Long id) {

        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Transactional
    public T getById(Long id) {

        return entityManager.find(entityClass, id);
    }

    protected <E extends AbstractEntityWithGeneratedId> E getById(Class<E> clazz, Long id) {

        return entityManager.find(clazz, id);
    }

    @Transactional
    public boolean exists(Long id) {

        if (id == null) {

            return false;
        }

        return findById(id).isPresent();
    }

    protected EntityManager getEntityManager() {

        return entityManager;
    }
}
