package ru.bellintegrator.practice.dao.Impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.RegUserDAO;
import ru.bellintegrator.practice.dao.UserDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.model.*;
import ru.bellintegrator.practice.view.UserFilterView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RegUserDAOImpl implements RegUserDAO {

    private final EntityManager em;
    @Autowired
    public RegUserDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void save(RegUser RegUser) {
        em.persist(RegUser);
    }

    @Override
    public List<RegUser> all() {
        TypedQuery<RegUser> query = em.createQuery("SELECT p FROM RegUser p", RegUser.class);
        //TypedQuery<Office> query = em.createQuery("FROM Office", Office.class);
        return query.getResultList();
    }
}