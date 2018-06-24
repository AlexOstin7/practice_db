package ru.bellintegrator.practice.service;

import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.OrganizationFilterView;
import ru.bellintegrator.practice.view.OrganizationView;


import java.util.List;

/**
 * Some service. Just for test
 */
public interface OrganizationService {

    void add(OrganizationView organization);

    void updateOrganization(OrganizationView organization);

    List<OrganizationFilterView> filterOrganizationList(OrganizationFilterView organizationFilterView);

    void deleteOrganization(OrganizationView organization);

    List<OrganizationView> organizations();

    Organization getOrganizationById(Long id);

   // Organization getOrganizationByName(String name);


}