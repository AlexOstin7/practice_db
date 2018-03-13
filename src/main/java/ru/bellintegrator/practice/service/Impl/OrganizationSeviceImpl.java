package ru.bellintegrator.practice.service.Impl;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.OrganizationDAO;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.OrganizationService;

import javax.transaction.Transactional;

@Service
public class OrganizationSeviceImpl implements OrganizationService {

    private OrganizationDAO organizationDAO;
    @Override
    @Transactional
    public void addOrganization(Organization organization) {

    }
}
