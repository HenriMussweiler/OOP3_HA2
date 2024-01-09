package awk.fuhrparkverwaltung.usecases.impl;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class GenericDAO<T> {

    private final String UNIT_NAME = "OOP3-HA2-Fuhrparkverwaltung";

    private EntityManagerFactory emf;
    private EntityManager em;

    private Class<T> entityClass;

    public GenericDAO() {
        emf = Persistence.createEntityManagerFactory(UNIT_NAME);
        em = emf.createEntityManager();
    }

    public GenericDAO(Class<T> entityClass) {
        this();
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(entity);
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public T update(T entity) {
        EntityTransaction tr = em.getTransaction();
        T ret;
        try {
            tr.begin();
            ret = em.merge(entity);
            tr.commit();
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            System.out.println("Fehler beim Update: " + e.getMessage());
            throw e; // Re-throw the exception after rolling back the transaction
        }
        return ret;
    }

    protected boolean delete(Object id, Class<T> classe) {
        EntityTransaction tr = em.getTransaction();
        T entityToBeRemoved = em.getReference(classe, id);
        try {
            tr.begin();
            em.remove(entityToBeRemoved);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            System.out.println("Fehler beim Löschen der Id: " + id.toString());
            return false;
        }
    }

    public T find(long entityId) {
        return em.find(entityClass, entityId);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
        try {
            EntityTransaction tr = em.getTransaction();
            tr.begin();

            Query query = em.createNamedQuery(namedQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (T) query.getSingleResult();

            tr.commit();
        } catch (Exception e) {
            System.out.println("Fehler bei der Query: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<T> findListResult(String namedQuery, Map<String, Object> parameters) {
        List<T> result = null;
        try {
            EntityTransaction tr = em.getTransaction();
            tr.begin();

            Query query = em.createNamedQuery(namedQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = (List<T>) query.getResultList();

            tr.commit();
        } catch (Exception e) {
            System.out.println("Fehler bei der Query: " + e.getMessage());
        }
        return result;
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

    public void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
