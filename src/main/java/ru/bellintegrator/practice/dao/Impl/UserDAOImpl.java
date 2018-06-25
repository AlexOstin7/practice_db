package ru.bellintegrator.practice.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.UserDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.model.User;
//import ru.bellintegrator.practice.view.UserFilterView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager em;

    //private EntityManager entityManager;
    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<User> all() {
        TypedQuery<User> query = em.createQuery("SELECT p FROM User p", User.class);
        //TypedQuery<User> query = em.createQuery("FROM User", User.class);
        return query.getResultList();
    }


    @Override
    public User loadById(Long id) {
        if (id > 0 ) {
            return   em.find(User.class, id);

        } else {
            throw new CustomErrorException(String.format("DAO says Mismached parametr- Id* is %s", id));
        }

    }


    @Override
    public void save(User User) {
        em.persist(User);
    }

    @Override
    public void remove(User User) {
        em.remove(User);
    }


    @Override
    public User loadByName(String name, Long inn, Boolean isActive) {
        return em.find(User.class, name);
    }

    @Override
    public User loadByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> User = criteria.from(User.class);
        criteria.where(builder.equal(User.get("name"), name));

        TypedQuery<User> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

   /* @Override
    public List<User> filterUserList(UserFilterView userFilterView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> User = criteria.from(User.class);
        //criteria.where(builder.and(builder.equal(User.get("organization").get("id"), userFilterView.getOrgId()), builder.like(User.get("name"), "%" + userFilterView.getName() + "%"), builder.like(User.get("phone").as(String.class), "%" + String.valueOf(userFilterView.getPhone()) + "%"), builder.equal(User.get("isActive"), userFilterView.getActive())));
        if (userFilterView.getName() != null && userFilterView.getPhone() != null && userFilterView.getActive() != null) {
            criteria.where(builder.and(builder.equal(User.get("organization").get("id"), userFilterView.getOrgId()), builder.like(builder.lower(User.get("name")), "%" + userFilterView.getName().toLowerCase() + "%"), builder.like(User.get("phone").as(String.class), "%" + String.valueOf(userFilterView.getPhone()) + "%"), builder.equal(User.get("isActive"), userFilterView.getActive())));
        } else if (userFilterView.getName() == null && userFilterView.getPhone() != null && userFilterView.getActive() != null) {
            criteria.where(builder.and(builder.equal(User.get("organization").get("id"), userFilterView.getOrgId()), builder.like(User.get("phone").as(String.class), "%" + String.valueOf(userFilterView.getPhone()) + "%"), builder.equal(User.get("isActive"), userFilterView.getActive())));
        } else if (userFilterView.getName() != null && userFilterView.getPhone() == null && userFilterView.getActive() != null) {
            criteria.where(builder.and(builder.equal(User.get("organization").get("id"), userFilterView.getOrgId()), builder.like(builder.lower(User.get("name")), "%" + userFilterView.getName().toLowerCase() + "%"), builder.equal(User.get("isActive"), userFilterView.getActive())));
        } else if (userFilterView.getName() != null && userFilterView.getPhone() != null && userFilterView.getActive() == null) {
            criteria.where(builder.and(builder.equal(User.get("organization").get("id"), userFilterView.getOrgId()), builder.like(builder.lower(User.get("name")), "%" + userFilterView.getName().toLowerCase() + "%"), builder.like(User.get("phone").as(String.class), "%" + String.valueOf(userFilterView.getPhone()) + "%")));
        } else if (userFilterView.getName() == null && userFilterView.getPhone() == null && userFilterView.getActive() != null) {
            criteria.where(builder.and(builder.equal(User.get("organization").get("id"), userFilterView.getOrgId()), builder.equal(User.get("isActive"), userFilterView.getActive())));
        } else if (userFilterView.getName() == null && userFilterView.getPhone() != null && userFilterView.getActive() == null) {
            criteria.where(builder.and(builder.equal(User.get("organization").get("id"), userFilterView.getOrgId()), builder.like(User.get("phone").as(String.class), "%" + String.valueOf(userFilterView.getPhone()) + "%")));
        } else if (userFilterView.getName() != null && userFilterView.getPhone() == null && userFilterView.getActive() == null) {
            criteria.where(builder.and(builder.equal(User.get("organization").get("id"), userFilterView.getOrgId()), builder.like(builder.lower(User.get("name")), "%" + userFilterView.getName().toLowerCase() + "%")));
        }  else if (userFilterView.getName() == null && userFilterView.getPhone() == null && userFilterView.getActive() == null) {
            criteria.where(builder.equal(User.get("organization").get("id"), userFilterView.getOrgId()));
        }
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getResultList();

    }
*/
}