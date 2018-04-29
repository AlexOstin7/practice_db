package ru.bellintegrator.practice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.message.Message;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;

public interface OrganizationController {

    Response organization(@RequestBody OrganizationView organization);

    Response updateOrganizaton(@RequestBody OrganizationView organization);

    Message deleteOrganizaton(@RequestBody OrganizationView organization);

    Message organizations();

    Response organizations(@RequestBody OrganizationView organization);

    Response getOrganizationById(@PathVariable("id") Long id) ;

}
