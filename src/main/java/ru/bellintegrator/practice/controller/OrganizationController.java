package ru.bellintegrator.practice.controller;

import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;

public interface OrganizationController {
    /**
     * Dummy controller
     * Just for testing
     */
    String ping();

    /**
     * Add person
     * @param organization
     */
    void organization(@RequestBody OrganizationView organization);

    /**
     * Get all persons
     * @return JSON persons value
     */
    List<OrganizationView> organizations();
}
