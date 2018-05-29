package ru.bellintegrator.practice.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OfficeDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.OfficeFilterView;

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
        if (id > 0 ) {
            return   em.find(Office.class, id);

        } else {
            throw new CustomErrorException(String.format("Mismached parametr- Id* is %s", id));
        }

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

    @Override
    public List<Office> filterOfficeList(OfficeFilterView officeFilterView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);

        Root<Office> Office = criteria.from(Office.class);
        //criteria.where(builder.and(builder.equal(Office.get("organization").get("id"), officeFilterView.getOrgId()), builder.like(Office.get("name"), "%" + officeFilterView.getName() + "%"), builder.like(Office.get("phone").as(String.class), "%" + String.valueOf(officeFilterView.getPhone()) + "%"), builder.equal(Office.get("isActive"), officeFilterView.getActive())));
        if (officeFilterView.getName() != null && officeFilterView.getPhone() != null && officeFilterView.getActive() != null) {
            criteria.where(builder.and(builder.equal(Office.get("organization").get("id"), officeFilterView.getOrgId()), builder.like(builder.lower(Office.get("name")), "%" + officeFilterView.getName().toLowerCase() + "%"), builder.like(Office.get("phone").as(String.class), "%" + String.valueOf(officeFilterView.getPhone()) + "%"), builder.equal(Office.get("isActive"), officeFilterView.getActive())));
        } else if (officeFilterView.getName() == null && officeFilterView.getPhone() != null && officeFilterView.getActive() != null) {
            criteria.where(builder.and(builder.equal(Office.get("organization").get("id"), officeFilterView.getOrgId()), builder.like(Office.get("phone").as(String.class), "%" + String.valueOf(officeFilterView.getPhone()) + "%"), builder.equal(Office.get("isActive"), officeFilterView.getActive())));
        } else if (officeFilterView.getName() != null && officeFilterView.getPhone() == null && officeFilterView.getActive() != null) {
            criteria.where(builder.and(builder.equal(Office.get("organization").get("id"), officeFilterView.getOrgId()), builder.like(builder.lower(Office.get("name")), "%" + officeFilterView.getName().toLowerCase() + "%"), builder.equal(Office.get("isActive"), officeFilterView.getActive())));
        } else if (officeFilterView.getName() != null && officeFilterView.getPhone() != null && officeFilterView.getActive() == null) {
            criteria.where(builder.and(builder.equal(Office.get("organization").get("id"), officeFilterView.getOrgId()), builder.like(builder.lower(Office.get("name")), "%" + officeFilterView.getName().toLowerCase() + "%"), builder.like(Office.get("phone").as(String.class), "%" + String.valueOf(officeFilterView.getPhone()) + "%")));
        } else if (officeFilterView.getName() == null && officeFilterView.getPhone() == null && officeFilterView.getActive() != null) {
            criteria.where(builder.and(builder.equal(Office.get("organization").get("id"), officeFilterView.getOrgId()), builder.equal(Office.get("isActive"), officeFilterView.getActive())));
        } else if (officeFilterView.getName() == null && officeFilterView.getPhone() != null && officeFilterView.getActive() == null) {
            criteria.where(builder.and(builder.equal(Office.get("organization").get("id"), officeFilterView.getOrgId()), builder.like(Office.get("phone").as(String.class), "%" + String.valueOf(officeFilterView.getPhone()) + "%")));
        } else if (officeFilterView.getName() != null && officeFilterView.getPhone() == null && officeFilterView.getActive() == null) {
            criteria.where(builder.and(builder.equal(Office.get("organization").get("id"), officeFilterView.getOrgId()), builder.like(builder.lower(Office.get("name")), "%" + officeFilterView.getName().toLowerCase() + "%")));
        }  else if (officeFilterView.getName() == null && officeFilterView.getPhone() == null && officeFilterView.getActive() == null) {
            criteria.where(builder.equal(Office.get("organization").get("id"), officeFilterView.getOrgId()));
            147896325
        }
        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getResultList();

    }
}