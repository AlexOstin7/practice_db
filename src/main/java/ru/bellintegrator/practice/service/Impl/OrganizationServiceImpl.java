package ru.bellintegrator.practice.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OrganizationDAO;
import ru.bellintegrator.practice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sun.jmx.snmp.ThreadContext.contains;
import static java.util.Collections.emptyList;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OrganizationServiceImpl implements OrganizationService {
    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationDAO dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(OrganizationView view) {
        Organization organization = new Organization(view.name, view.fullName, view.inn, view.kpp, view.address, view.phone, view.isActive);
        dao.save(organization);
    }

    @Override
    @Transactional
    public void updateOrganization(OrganizationView view) {
        Organization organization = dao.loadById(Long.valueOf(view.id));
        organization.setName(view.name);
        organization.setFullName(view.fullName);
        organization.setInn(view.inn);
        organization.setKpp(view.kpp);
        organization.setAddress(view.address);
        organization.setPhone(view.phone);
        organization.setActive(view.isActive);

        log.info(view.toString());

        dao.save(organization);
    }

    @Override
    @Transactional
    public void deleteOrganization(OrganizationView view) {
        Organization organization = dao.loadById(Long.valueOf(view.getId()));
        log.info(view.toString());
        if (organization == null) {
            throw new CustomNotFoundException("Not found organizaton with Id is " + view.getId());
        }
            dao.remove(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> organizations() {
        List<Organization> all = dao.all();

        Function<Organization, OrganizationView> mapOrganization = p -> {
            OrganizationView view = new OrganizationView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.fullName = p.getFullName();
            view.inn = p.getInn();
            view.kpp = p.getKpp();
            view.address = p.getAddress();
            view.phone = p.getPhone();
            view.isActive = p.getActive();

            log.info(view.toString());

            return view;
        };
        return all.stream()
                .map(mapOrganization)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> listOrganizations(OrganizationView organization) {
        List<Organization> all = dao.all();
        Function<Organization, OrganizationView> mapOrganization = p -> {
            OrganizationView view = new OrganizationView();
                view.id = String.valueOf(p.getId());
                view.name = p.getName();
                view.inn = p.getInn();
                view.isActive = p.getActive();

            log.info(view.toString());

            return view;
        };
        if (organization.getName() != null && !organization.getName().isEmpty()) {
           return
                   all.stream().map(mapOrganization)
                    .filter(p -> (
                                    ( p.getActive() == organization.getActive() && p.getName().contains(organization.getName()) && String.valueOf(p.getInn()).contains(String.valueOf(organization.getInn())) )

                                    || (
                                            p.getName().contains(organization.getName()) && organization.getActive() == null && p.getName().contains(organization.getName()) && organization.getInn() == null )
                                        )
                            ||      (
                                    (String.valueOf(p.getInn()).contains(String.valueOf(organization.getInn())) && p.getName().contains(organization.getName()) && organization.getActive() == null)
                                    )
                            
                            ||      (
                                    p.getName().contains(organization.getName()) && p.getActive() == organization.getActive() && organization.getInn() == null
                                    )
                                )
                    .collect(Collectors.toList());
        }
            else {throw new CustomNotFoundException(String.format("Invalid request Name*- %s Inn- %s Active- %s ", organization.getId(), organization.getInn(), organization.isActive));//return Collections.emptyList();
        }
    }
    @Override
    @Transactional(readOnly = true)
    public Organization getOrganizationById(Long id) {
        return dao.loadById(id);
    }

}
