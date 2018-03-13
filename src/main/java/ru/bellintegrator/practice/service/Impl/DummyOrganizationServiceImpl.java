package ru.bellintegrator.practice.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OrganizationDAO;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.DummyOrganizationService;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class DummyOrganizationServiceImpl implements DummyOrganizationService {
    private final Logger log = LoggerFactory.getLogger(DummyOrganizationServiceImpl.class);

    private final OrganizationDAO dao;

    @Autowired
    public DummyOrganizationServiceImpl(OrganizationDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(OrganizationView view) {
        Organization organization = new Organization(view.name, view.inn);
        dao.save(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> organizations() {
        List<Organization> all = dao.all();

        Function<Organization, OrganizationView> mapOrganization = org -> {
            OrganizationView view = new OrganizationView();
            view.id = String.valueOf(org.getId());
            view.name = org.getName();
            view.inn = org.getInn();

            log.info(view.toString());

            return view;
        };

        return all.stream()
                .map(mapOrganization)
                .collect(Collectors.toList());
    }
}
