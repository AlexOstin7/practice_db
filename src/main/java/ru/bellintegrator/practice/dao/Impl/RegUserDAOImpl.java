package ru.bellintegrator.practice.dao.Impl;

import org.h2.jdbc.JdbcSQLException;
import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.RegUserDAO;
import ru.bellintegrator.practice.dao.UserDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.model.*;
import ru.bellintegrator.practice.service.Impl.OfficeServiceImpl;
import ru.bellintegrator.practice.service.Impl.RegUserServiceImpl;
import ru.bellintegrator.practice.service.Impl.UserServiceImpl;
import ru.bellintegrator.practice.view.UserFilterView;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RegUserDAOImpl implements RegUserDAO {

    private final EntityManager em;

    private final Logger log = LoggerFactory.getLogger(RegUserDAOImpl.class);

    @Autowired
    public RegUserDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void save(RegUser regUser) {
//
        try {
            em.persist(regUser);
            em.flush();
            log.info("dao save " + regUser.toString());
        } catch (Exception e) {
            throw new CustomErrorException("Ошибка сохранения в бд " + e.toString());
        }
    }

    @Override
    public RegUser loadByCode(String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<RegUser> criteria = builder.createQuery(RegUser.class);

        Root<RegUser> RegUser = criteria.from(RegUser.class);
        criteria.where(builder.equal(RegUser.get("code"), code));

        TypedQuery<RegUser> query = em.createQuery(criteria);

        try {
            return query.getSingleResult();
        } catch (NonUniqueResultException e) {
            throw new CustomErrorException(String.format("DAO RegUser loadByCode code is Unique*- %s %s", code, e.toString()));
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public RegUser loadByLogin(String login) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<RegUser> criteria = builder.createQuery(RegUser.class);

        Root<RegUser> RegUser = criteria.from(RegUser.class);
        criteria.where(builder.equal(RegUser.get("login"), login));

        TypedQuery<RegUser> query = em.createQuery(criteria);

        try {
            return query.getSingleResult();
        } catch (NonUniqueResultException e) {
            throw new CustomErrorException(String.format("DAO RegUser loadByLogin says login is Unique- %s", login));
        } catch (NoResultException e) {
            throw new CustomErrorException(String.format("DAO RegUser loadByLogin says login does't found- %s", login));
            //e.printStackTrace();
        }

    }

    @Override
    public RegUser loadByPassword(String password) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<RegUser> criteria = builder.createQuery(RegUser.class);

        Root<RegUser> RegUser = criteria.from(RegUser.class);
        criteria.where(builder.equal(RegUser.get("password"), password));

        TypedQuery<RegUser> query = em.createQuery(criteria);

        try {
            return query.getSingleResult();
        } catch (NonUniqueResultException e) {
            throw new CustomErrorException(String.format("DAO RegUser loadByPassword says login is Unique- %s", password));
        } catch (NoResultException e) {
            throw new CustomErrorException(String.format("DAO RegUser loadByPassword says login does't found- %s", password));
            //e.printStackTrace();
        }

    }

    //return em.find(RegUser.class, code);

    @Test
    @Override
    public List<RegUser> all() {
        TypedQuery<RegUser> query = em.createQuery("SELECT p FROM RegUser p", RegUser.class);
        //TypedQuery<Office> query = em.createQuery("FROM Office", Office.class);
        return query.getResultList();
    }
}