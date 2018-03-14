package ru.bellintegrator.practice.service;

import ru.bellintegrator.practice.view.OrganizationView;


import java.util.List;

/**
 * Some service. Just for test
 */
public interface OrganizationService {

    /**
     *
     * @param organization
     */
    void add(OrganizationView organization);

    /**
     * Dummy service method
     * @return {@Organization}
     */
    List<OrganizationView> organizations();
}