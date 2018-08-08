package ru.bellintegrator.practice.dao.Impl;

import com.sun.javafx.fxml.expression.Expression;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.UserDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.UserFilterView;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
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
            throw new CustomErrorException(String.format("DAO user loadByID says Mismached parametr- Id* is %s", id));
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

        Session session = em.unwrap(Session.class);
        session.beginTransaction();

        Criteria cr = session.createCriteria(User.class);
        cr.createCriteria("office", "office");
        cr.createCriteria("doc.countries", "country");
        cr.createCriteria("doc", "doc");
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Conjunction objConjunction = Restrictions.conjunction();

        objConjunction.add(Restrictions.eq("office.id", userFilterView.getOfficeId().longValue()));
        if (userFilterView.getFirstName() != null) {
            objConjunction.add(Restrictions.like("firstName", "%" + userFilterView.getFirstName() + "%").ignoreCase());
        }
        if (userFilterView.getSecondName() != null) {
            objConjunction.add(Restrictions.like("secondName", "%" + userFilterView.getSecondName() + "%").ignoreCase());
        }
        if (userFilterView.getMiddleName() != null) {
            objConjunction.add(Restrictions.like("middleName", "%" + userFilterView.getMiddleName() + "%").ignoreCase());
        }
        if (userFilterView.getPossition() != null) {
            objConjunction.add(Restrictions.like("possition", "%" + userFilterView.getPossition() + "%").ignoreCase());
        }
        if (userFilterView.getDocCode() != null) {
            objConjunction.add((Restrictions.eq("doc.code", userFilterView.getDocCode().intValue())));
        }
        if (userFilterView.getCitizenShipCode() != null) {
            objConjunction.add(Restrictions.eq("country.code", userFilterView.citizenShipCode.intValue()));
        }

        cr.add(objConjunction);

        List results = cr.list();
        session.close();
        return results;
    }

    /*@Override
    public List<Doc> loadDocs() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Doc> query = cb.createQuery(Doc.class);
        Root<User> userRoot = query.from(User.class);
        Join<User, Doc> docs = userRoot.join("doc");
        query.select(docs).where(cb.equal(userRoot.get("firstName"), "ИВАН"));
        List<Doc> results = em.createQuery(query).getResultList();

        return  results;
    }*/

    public List<Doc> allDocs() {
        //TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        TypedQuery<Doc> query = em.createQuery("FROM Doc", Doc.class);
        return query.getResultList();
    }

    public List<Country> allCountries() {
        //TypedQuery<Organization> query = em.createQuery("SELECT p FROM Organization p", Organization.class);
        TypedQuery<Country> query = em.createQuery("FROM Country", Country.class);
        return query.getResultList();
    }

    @Override
    public List<Doc> loadDocs(Integer id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        /*CriteriaQuery cq = cb.createQuery(Doc.class);
        Root root = cq.from(Doc.class);*/
/*
        Metamodel m = em.getMetamodel();
        EntityType<User> User_ = m.entity(User.class);
        EntityType<Doc> Doc_ = m.entity(Doc.class);*/

      /*  Root<User> user = cq.from(User.class);
        Root<Doc> doc = cq.from(Doc.class);*/

        //       cb.equal(root.join("doc").get("name"), "Свидетельство о рождении");

        //CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Doc> query = cb.createQuery(Doc.class);
        Root<Country> countryRoot = query.from(Country.class);
        Join<Country, Doc> docs = countryRoot.join("docs");
        query.select(docs).where(cb.equal(countryRoot.get("id"), id));
/*
// count books written by an author
        Subquery sub = cq.subquery(Long.class);
        Root subRoot = sub.from(Doc.class);
        //SetJoin<Doc, User> subAuthors = subRoot.join(User_,);
        Join<User,Doc> joinDoc = doc.join( User_.users);
        sub.select(cb.count(subRoot.get(Doc_.id)));
        sub.where(cb.equal(root.get(Author_.id), subAuthors.get(Author_.id)));

        Join<User,Doc> personAddress = user.fetch( User_. );
// Address.country is a ManyToOne
        Join<Address,Country> addressCountry = personAddress.fetch( Address_.country );

// check the result of the subquery
        cq.where(cb.greaterThanOrEqualTo(sub, 3L));*/

        //TypedQuery<Doc> query = em.createQuery(cq);
        //List<Doc> res = query.getResultList();
        List<Doc> results = em.createQuery(query).getResultList();

        return  results;
    }


}