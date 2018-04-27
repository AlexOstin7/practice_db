package ru.bellintegrator.practice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;

public interface OrganizationController {

    void organization(@RequestBody OrganizationView organization);

    void updateOrganizaton(@RequestBody OrganizationView organization);

    void deleteOrganizaton(@RequestBody OrganizationView organization);

    Response organizations();

    Response organizations(@RequestBody OrganizationView organization);

    Response getOrganizationById(@PathVariable("id") Long id) ;

    Response getOrganizationByName(String name);

}
