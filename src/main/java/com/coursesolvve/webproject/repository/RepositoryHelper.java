package com.coursesolvve.webproject.repository;

import org.springframework.stereotype.Component;
import com.coursesolvve.webproject.exception.EntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.UUID;

@Component
public class RepositoryHelper {

    @PersistenceContext
    private EntityManager entityManager;

    public <E> E getReferenceIfExist(Class<E> entityClass, UUID id) {
        validateExist(entityClass, id);
        return entityManager.getReference(entityClass, id);
    }

    public <E> void validateExist(Class<E> entityClass, UUID id) {
        Query query = entityManager.createQuery(
                "select count(e) from " + entityClass.getSimpleName()
                        + " e where e.id = :id");
        query.setParameter("id", id);
        boolean exists = ((Number) query.getSingleResult()).intValue() > 0;
        if (!exists) {
            throw new EntityNotFoundException(entityClass, id);
        }
    }

    public <E> E getEntityRequired(Class<E> entityClass, UUID id) {
        validateExist(entityClass, id);
        Query query = entityManager.createQuery(
                "select e from " + entityClass.getSimpleName()
                        + " e where e.id = :id");
        query.setParameter("id", id);
        return (E) query.getSingleResult();
    }
}
