package ru.bellintegrator.practice.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OfficeDAO;
import ru.bellintegrator.practice.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OfficeDAOImpl implements OfficeDAO {

    private final EntityManager em;
    //private EntityManager entityManager;
    @Autowired
    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Office> all() {
        TypedQuery<Office> query = em.createQuery("SELECT p FROM Office p", Office.class);
        //TypedQuery<Office> query = em.createQuery("FROM Office", Office.class);
        return query.getResultList();
    }


    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    }


    @Override
    public void save(Office Office) {
        em.persist(Office);
    }

    @Override
    public void remove(Office Office) {
        em.remove(Office);
    }


    @Override
    public Office loadByName(String name, Long inn, Boolean isActive) {
        return em.find(Office.class, name);
    }

    @Override
    public Office loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

        Root<Office> Office = criteria.from(Office.class);
        criteria.where(builder.equal(Office.get("name"), name));

        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

}