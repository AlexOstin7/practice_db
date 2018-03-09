package ru.bellintegrator.practice.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
private SessionFactory sessionFactory;

public void SessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
}

    @Override
    public void addOrganization(Organization organization) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(organization);

    }

    @Override
    public void updateOrganization(Organization organization) {

    }

    @Override
    public void removeOrganizarion(Long id) {

    }

    @Override
    public void getOrganizationById(Long id) {

    }
}
