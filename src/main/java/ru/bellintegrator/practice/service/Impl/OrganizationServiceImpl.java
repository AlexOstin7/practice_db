package ru.bellintegrator.practice.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OfficeDAO;
import ru.bellintegrator.practice.dao.OrganizationDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.OrganizationFilterView;
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
    public OrganizationServiceImpl(OrganizationDAO dao, OfficeDAO daoOffice) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(OrganizationView view) {
        log.info("org serv add before " + view.toString());
        Organization organization = new Organization(view.name, view.fullName, view.inn, view.kpp, view.address, view.phone, view.isActive);
        log.info("org serv add before " + view.toString());
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
        log.info("Orgnanization service remove view " + view.toString());
        if (organization == null) {
            throw new CustomErrorException("Not found organizaton with Id is " + view.getId());
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
    public List<OrganizationFilterView> filterOrganizationList(OrganizationFilterView organizationFilterView) {
        log.info("org serv filter view " + organizationFilterView.toString());
        if (organizationFilterView.getName() == null || organizationFilterView.getName().isEmpty()) {
            throw new CustomErrorException("Invalid request Name*- is empty  ");
        }
        log.info("2 " + organizationFilterView.toString());
        List<Organization> all = dao.filterOrganizationList(organizationFilterView);

        Function<Organization, OrganizationFilterView> mapOrganization = p -> {
            OrganizationFilterView view = new OrganizationFilterView();
            //view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.inn = p.getInn();
            view.isActive = p.getActive();

            log.info(view.toString());

            return view;
        };

        return all.stream().map(mapOrganization).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Organization getOrganizationById(Long id) {
        Organization organization = dao.loadById(id);
        if (organization == null) {
            throw new CustomErrorException(String.format("Service says Mismatch parametr- Id* is %s", id));
        }
        if (id < 1) {
            throw new CustomErrorException(String.format("Service says Mismatch parametr- Id* is %s", id));
        }
        log.info("organization service getId " + organization.toString());
        return organization;
    }

}
