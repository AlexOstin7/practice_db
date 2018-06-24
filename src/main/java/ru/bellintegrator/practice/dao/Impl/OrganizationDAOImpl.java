package ru.bellintegrator.practice.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OrganizationDAO;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.Impl.OfficeServiceImpl;
import ru.bellintegrator.practice.view.OrganizationFilterView;
import ru.bellintegrator.practice.view.OrganizationView;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OrganizationDAOImpl implements OrganizationDAO {

    private final EntityManager em;

    //private EntityManager entityManager;
    @Autowired
    public OrganizationDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        //TypedQuery<Organization> query = em.createQuery("FROM Organization", Organization.class);
        return query.getResultList();
    }


    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    }


    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    public void remove(Organization organization) {
        em.remove(organization);
    }

    @Override
    public List<Organization> filterOrganizationList(OrganizationFilterView organizationFilterView) {

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> Organization = criteria.from(Organization.class);

        if (organizationFilterView.getName() != null && organizationFilterView.getInn() != null && organizationFilterView.getActive() != null) {

            criteria.where(builder.and(builder.like(builder.lower(Organization.get("name")), "%" + organizationFilterView.getName().toLowerCase() + "%"), builder.like(Organization.get("inn").as(String.class), "%" + String.valueOf(organizationFilterView.getInn()) + "%"), builder.equal(Organization.get("isActive"), organizationFilterView.getActive())));

        } else if (organizationFilterView.getName() != null && organizationFilterView.getInn() == null && organizationFilterView.getActive() != null) {

            criteria.where(builder.and(builder.like(builder.lower(Organization.get("name")), "%" + organizationFilterView.getName().toLowerCase() + "%"), builder.equal(Organization.get("isActive"), organizationFilterView.getActive())));

        } else if (organizationFilterView.getName() != null && organizationFilterView.getInn() != null && organizationFilterView.getActive() == null) {

            criteria.where(builder.and(builder.like(builder.lower(Organization.get("name")), "%" + organizationFilterView.getName().toLowerCase() + "%"), builder.like(Organization.get("inn").as(String.class), "%" + String.valueOf(organizationFilterView.getInn()) + "%")));

        } else if (organizationFilterView.getName() != null && organizationFilterView.getInn() == null && organizationFilterView.getActive() == null) {

            criteria.where(builder.like(builder.lower(Organization.get("name")), "%" + organizationFilterView.getName().toLowerCase() + "%"));
        }

        TypedQuery<Organization> query = em.createQuery(criteria);

        return query.getResultList();
    }
}