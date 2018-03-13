package ru.bellintegrator.practice.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OrganizationDAO;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    private final EntityManager em;
    // @PersistenceContext(unitName="entityManagerFactory")
    private EntityManager entityManager;
    @Autowired
    public OrganizationDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> all() {

        TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        //TypedQuery<Organization> query = em.createQuery("FROM Organization", Organization.class);

        return query.getResultList();


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> Organization = criteria.from(Organization.class);
        criteria.where(builder.equal(Organization.get("name"), name));

        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization Organization) {
        em.persist(Organization);
    }

}