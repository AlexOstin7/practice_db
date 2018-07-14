package ru.bellintegrator.practice.dao.Impl;

import com.sun.javafx.fxml.expression.Expression;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.UserDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.UserFilterView;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager em;
    //private EntityManager em;

    //private EntityManager entityManager;
    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }
    //private SessionFactory sessionFactory = new Configuration().buildSessionFactory();
    //Session session = (Session) em.getDelegate();

    //SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
    //Session session = sessionFactory.openSession();

    //Session session = sessionFactory.getCurrentSession();

    @Override
    public List<User> all() {
        //TypedQuery<User> query = em.createQuery("SELECT p FROM User p", User.class);
        TypedQuery<User> query = em.createQuery("FROM User", User.class);
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
        criteria.where(builder.equal(User.get("firstName"), name));

        TypedQuery<User> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public List<User> filterUserList(UserFilterView userFilterView) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> User = criteria.from(User.class);

        Session session = em.unwrap(Session.class);
        //criteria.where(builder.and(builder.equal(User.get("office").get("id"), userFilterView.getOfficeId()), builder.like(User.get("firstName"), "%" + userFilterView.getFirstName() + "%"), builder.like(builder.lower(User.get("secondName")), "%" + userFilterView.getSecondName().toLowerCase() + "%"), builder.like(builder.lower(User.get("middleName")), "%" + userFilterView.getMiddleName().toLowerCase() + "%")));
        /*if (userFilterView.getFirstName() != null && userFilterView.getSecondName() != null && userFilterView.getMiddleName() != null && userFilterView.getPossition() != null) {

            criteria.where(builder.and(builder.equal(User.get("office").get("id"), userFilterView.getOfficeId()),
                                        builder.like(builder.lower(User.get("firstName")), "%" + userFilterView.getFirstName().toLowerCase() + "%"),
                                        builder.like(builder.lower(User.get("secondName")), "%" + userFilterView.getSecondName().toLowerCase() + "%"),
                                        builder.like(builder.lower(User.get("middleName")), "%" + userFilterView.getMiddleName().toLowerCase() + "%"),
                                        builder.like(builder.lower(User.get("possition")), "%" + userFilterView.getPossition().toLowerCase() + "%")));

        } else if (userFilterView.getFirstName() == null && userFilterView.getSecondName() != null && userFilterView.getMiddleName() != null && userFilterView.getPossition() != null) {

            criteria.where(builder.and(builder.equal(User.get("office").get("id"), userFilterView.getOfficeId()),
                                        builder.like(builder.lower(User.get("secondName")), "%" + userFilterView.getSecondName().toLowerCase() + "%"),
                                        builder.like(builder.lower(User.get("middleName")), "%" + userFilterView.getMiddleName().toLowerCase() + "%"),
                                        builder.like(builder.lower(User.get("possition")), "%" + userFilterView.getPossition().toLowerCase() + "%")));

        } else if (userFilterView.getFirstName() != null && userFilterView.getSecondName() == null && userFilterView.getMiddleName() != null && userFilterView.getPossition() != null) {

            criteria.where(builder.and(builder.equal(User.get("office").get("id"), userFilterView.getOfficeId()),
                                        builder.like(builder.lower(User.get("firstName")), "%" + userFilterView.getFirstName().toLowerCase() + "%"),
                                        builder.like(builder.lower(User.get("middleName")), "%" + userFilterView.getMiddleName().toLowerCase() + "%"),
                                        builder.like(builder.lower(User.get("possition")), "%" + userFilterView.getPossition().toLowerCase() + "%")));

        } else if (userFilterView.getFirstName() != null && userFilterView.getSecondName() != null && userFilterView.getMiddleName() == null) {
            criteria.where(builder.and(builder.equal(User.get("office").get("id"), userFilterView.getOfficeId()), builder.like(builder.lower(User.get("firstName")), "%" + userFilterView.getFirstName().toLowerCase() + "%"), builder.like(builder.lower(User.get("secondName")), "%" + userFilterView.getSecondName().toLowerCase() + "%")));
        } else if (userFilterView.getFirstName() == null && userFilterView.getSecondName() == null && userFilterView.getMiddleName() != null) {
            criteria.where(builder.and(builder.equal(User.get("office").get("id"), userFilterView.getOfficeId()), builder.like(builder.lower(User.get("middleName")), "%" + userFilterView.getMiddleName().toLowerCase() + "%")));
        } else if (userFilterView.getFirstName() == null && userFilterView.getSecondName() != null && userFilterView.getMiddleName() == null) {
            criteria.where(builder.and(builder.equal(User.get("office").get("id"), userFilterView.getOfficeId()), builder.like(builder.lower(User.get("secondName")), "%" + userFilterView.getSecondName().toLowerCase() + "%")));
        } else if (userFilterView.getFirstName() != null && userFilterView.getSecondName() == null && userFilterView.getMiddleName() == null) {
            criteria.where(builder.and(builder.equal(User.get("office").get("id"), userFilterView.getOfficeId()), builder.like(builder.lower(User.get("firstName")), "%" + userFilterView.getFirstName().toLowerCase() + "%")));
        }  else if (userFilterView.getFirstName() == null && userFilterView.getSecondName() == null && userFilterView.getMiddleName() == null) {
            criteria.where(builder.equal(User.get("office").get("id"), userFilterView.getOfficeId()));
        }
        TypedQuery<User> query = em.createQuery(criteria);
        return query.getResultList();*/
        session.beginTransaction();

        Criteria cr = session.createCriteria(User.class);
        Criteria cr2 = session.createCriteria(Country.class);
        //cr.add(Restrictions.isNotNull("firstName")).add(Restrictions.isNotNull("secondName")).add(Restrictions.isNotNull("middleName")).add(Restrictions.isNotNull("possition"));
        //cr.add(Restrictions.isNotEmpty("firstName")).add(Restrictions.isNotEmpty("secondName")).add(Restrictions.isNotEmpty("middleName")).add(Restrictions.isNotEmpty("possition"));
//         cr.add(Restrictions.like("firstName".toLowerCase(), userFilterView.getFirstName().toLowerCase()));
        cr.createCriteria("office", "office");
        cr.createCriteria("doc.countries", "country");
       // cr.createCriteria("country", "country");
       // cr.createCriteria("doc.countries.docs", "docs");
        cr.createCriteria("doc", "doc");
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        cr.add(Restrictions.eq("office.id", userFilterView.getOfficeId().longValue()));
        cr.add(Restrictions.like("firstName", "%" + userFilterView.getFirstName() + "%").ignoreCase());
        cr.add(Restrictions.like("secondName", "%" + userFilterView.getSecondName() + "%").ignoreCase());
        cr.add(Restrictions.like("middleName", "%" + userFilterView.getMiddleName() + "%").ignoreCase());
        cr.add(Restrictions.like("possition", "%" + userFilterView.getPossition() + "%").ignoreCase());
        cr.add(Restrictions.eq("doc.code", userFilterView.docCode.intValue()));
        //cr.add(Restrictions.eq("country.code", 643));
        cr.add(Restrictions.eq("country.code", userFilterView.citizenShipCode.intValue()));

        List results = cr.list();
        session.close();
        return results;
        // return null;
    }

}